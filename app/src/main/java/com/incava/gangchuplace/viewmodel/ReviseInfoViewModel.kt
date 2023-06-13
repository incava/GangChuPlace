package com.incava.gangchuplace.viewmodel

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.incava.gangchuplace.application.GlobalApplication
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.Prefs
import com.incava.gangchuplace.util.ProfileCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File

class ReviseInfoViewModel : ViewModel() {

    //이미지 경로가 있는 state 변수
    //이미지 uri를 가지고 있는 변수
    var image = MutableLiveData(GlobalApplication.prefs.getSharedPreference().image)

    var nickname = ""

    var resultEvent = MutableLiveData<ProfileCode>()


    init {
        nickname = GlobalApplication.prefs.getSharedPreference().nickname
    }

    fun setNickName(nickname: String){
        this.nickname = nickname
    }

    //닉네임 다시 정하기 메서드
    fun renameNickname() {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val id = GlobalApplication.prefs.getSharedPreference()
                        .run { "${loginRoute}+${email}" }
                    Log.i("image",image.value.toString())
                    //만약 기존의 닉네임과 다르다면 실행.
                    if (GlobalApplication.prefs.getSharedPreference().nickname != nickname){
                        val nicknameQuery = Common.fireStore.collection("User")
                            .whereEqualTo("nickname", nickname)
                            .get()
                            .await()
                        if (!nicknameQuery.isEmpty) {
                            // 이미 있는 닉네임
                            resultEvent.postValue(ProfileCode.ExistNickName)
                            return@launch
                        }
                    }
                    // 만약 이미 있는 http로 시작하는 주소일 경우 -> 내 휴대폰의 정보가 아닌 파이어베이스에 있는 주소라는뜻.
                    // 기존의 이미지와 다르다는 뜻.
                    if ((GlobalApplication.prefs.getSharedPreference().image != image.value)){
                        val imageUrl = uploadStorage(id, "Profile", "profile")
                        val updatedData = hashMapOf(
                            "image" to imageUrl,
                            "nickname" to nickname
                        )
                        Common.fireStore.collection("User")
                            .document(id)
                            .update(updatedData as Map<String, Any>)
                            .await()
                        //아까 사용한 맵을 가지고 shared에도 동기화.
                        GlobalApplication.prefs.setSharedPreference(updatedData)
                    }
                    resultEvent.postValue(ProfileCode.TransformSuccess) // 성공 로직
                }catch (e : Exception){
                    resultEvent.postValue(ProfileCode.UpLoadFail)
                }
        }
    }

    //스토리지 Review에 사진을 업로드 하는 메서드
    private suspend fun uploadStorage(id: String, position: String, title: String): String {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("images").child(id).child(position)
        val file = Uri.fromFile(File(image.value ?: ""))
        val imageRef = imagesRef.child(title)
        val uploadTask = imageRef.putFile(file)
        uploadTask.await()
        return imageRef.downloadUrl.await().toString() //url을 다운 받은 후 리턴.
    }


}