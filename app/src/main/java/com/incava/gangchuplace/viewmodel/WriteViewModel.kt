package com.incava.gangchuplace.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.adapter.Common.getCurrentDateTime
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import com.incava.gangchuplace.model.ReviewDTO
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.model.UserDTO
import com.incava.gangchuplace.view.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.StringBuilder

class WriteViewModel : ViewModel() {


    // 리뷰를 적을 가게 정보
    private lateinit var storePlace: StorePlace

    // 장소 만족도
    private var ratingBarRank: Float = 5.0f

    //리뷰 내용
    private var editReview = ""

    //이미지 uri를 가지고 있는 변수
    var image = MutableLiveData("")

    //프로그레스바 반응 변수
    var isLoading = MutableLiveData(false)

    //sharePreference에서 가져온 id
    private var id = ""

    //sharePreference에서 가져온 loginRoute
    private var loginRoute = ""

    fun setStorePlace(storePlace: StorePlace){
        this.storePlace = storePlace
        Log.i("storePlace",storePlace.toString())
    }

    //ratingBar와 연결된 메서드
    fun setRatingBarRank(rating: Float) {
        ratingBarRank = rating
    }

    //editText와 연결된 메서드. 완료시 editReview에 할당.
    fun setEditReview(text: String) {
        editReview = text
    }

    fun removeImage() {
        image.postValue("")
    }

    fun finishReview(view: View) {
        isLoading.postValue(true)
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {

                    // sharedPreference 파일 접근.
                    getSharedPreference(view).also {
                        id = it.id
                        loginRoute = it.loginRoute
                    }

                    //사진을 스토리지에 업로드 및 image저장.
                    val imageUri = uploadStorage()

                    val time = getCurrentDateTime()
                    // 보낼 reviewDTO
                    val reviewDTO = ReviewDTO(
                        timeStamp = time,
                        rank = ratingBarRank.toString(),
                        body = editReview,
                        store = storePlace.title,
                        image = imageUri
                    )

                    //자신Review 대한 저장.
                    val reviewResult = fireStore.collection("User")
                        .document("${loginRoute}+${id}")
                        .collection("Review")
                        .document(storePlace.title)
                        .set(reviewDTO)
                        .await()

                    //Store 컬렉션에 저장 결과
                    val storeResult = fireStore.collection("Store")
                        .document(storePlace.title)
                        .set(storePlace)
                        .await()

                    // Store field중 Review라는 컬렉션에 자신 Review컬렉션에 Document를 저장.
                    val storeReviewResult = fireStore.collection("Store")
                        .document(storePlace.title)
                        .collection("Review")
                        .document("${loginRoute}+${id}")
                        .set(mapOf("time" to time))
                        .await()

                    //코루틴의 await()로 작업이 모두 성공적(catch가 아닐때) Toast호출하도록.
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(view.context, "리뷰 작성 완료!", Toast.LENGTH_SHORT).show()
                        (view.context as MainActivity).findNavController(R.id.main_nav_host)
                            .navigate(R.id.action_global_baseContainerFragment)
                    }

                } catch (e: Exception) {
                    Log.i("error", e.message.toString())
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(view.context, "리뷰 작성 실패!", Toast.LENGTH_SHORT).show()
                    }
                }
                isLoading.postValue(false)
                Log.i("image의 값", image.value.toString())
            }
        }
    }

    //스토리지에 사진을 업로드 하는 메서드
    private suspend fun uploadStorage(): String {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("images")
        val file = Uri.fromFile(File(image.value ?: ""))
        val imageRef = imagesRef.child("image.jpg")
        val uploadTask = imageRef.putFile(file)
        uploadTask.await()
        return imageRef.downloadUrl.await().toString() //url을 다운 받은 후 리턴.
    }


}