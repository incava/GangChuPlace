package com.incava.gangchuplace.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.adapter.Common.getCurrentDateTime
import com.incava.gangchuplace.model.ReviewDTO
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.UserDTO
import com.incava.gangchuplace.view.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder

class WriteViewModel : ViewModel() {


    private var _placeList = MutableLiveData<MutableList<StorePlace>>()
    val placeList: MutableLiveData<MutableList<StorePlace>> get() = _placeList

    // 리뷰를 적을 가게 정보
    private lateinit var storePlace: StorePlace

    // 장소 만족도
    private var ratingBarRank: Float = 5.0f

    //리뷰 내용
    private var editReview = ""


    init {
        val a = mutableListOf<StorePlace>()
        repeat(6) {
            a.add(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"))
        }
        _placeList.value = a
    }

    //ratingBar와 연결된 메서드
    fun setRatingBarRank(rating: Float) {
        ratingBarRank = rating
    }

    //editText와 연결된 메서드. 완료시 editReview에 할당.
    fun setEditReview(text: String) {
        editReview = text
    }

    //버튼을 눌렀을때,
    fun moveNext(view: View, store: StorePlace) {
        storePlace = store
        Toast.makeText(view.context, storePlace.toString(), Toast.LENGTH_SHORT).show()
        view.findNavController().navigate(R.id.action_writeSearchFragment_to_writeReviewFragment)
    }

    fun finishReview(view: View) {
        // 저장하는 기능 수행.
        //다 수행하고 나서 다시 처음으로 돌아가기.
        updateReview(view)
    }

    fun updateReview(view: View) {
        // sharedPreference 파일 접근.
        val sharedPreferences =
            view.context.getSharedPreferences("userInfo", Context.MODE_PRIVATE) ?: return
        val id = sharedPreferences.getString("id", "null")
        val loginRoute = sharedPreferences.getString("loginRoute", "null")
        val time = getCurrentDateTime()
        // 보낼 reviewDTO
        val reviewDTO = ReviewDTO(
            time,
            rank = ratingBarRank.toString(),
            body = editReview,
            store = storePlace.title
        )

        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
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
            }
        }
    }


}