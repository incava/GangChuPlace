package com.incava.gangchuplace.viewmodel.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.incava.gangchuplace.model.UserDTO
import com.incava.gangchuplace.util.Common.fireStore
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginRepo(private val application: Application) {

    val checkLogin by lazy { MutableLiveData<Boolean>() }


    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (token != null) {
            Log.i(TAG, "카카오 로그인 성공 ${token.accessToken}")
            UserApiClient.instance.me { user, error ->
                if (user != null) {
                    //DTO만들 값 저장.
                    val image = user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""
                    val loginRoute = "kakao"
                    val id = "${loginRoute}+${user.id}"
                    val userDTO = UserDTO(
                        nickname = id + "0",
                        image = image,
                        loginRoute = loginRoute,
                        password = ""
                    )
                    saveInfo(userDTO, id)
                    checkLogin.postValue(true)
                    Log.i(
                        "user정보",
                        "${user.id.toString()} + ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                    )
                } else {
                    Log.e(TAG, "카카오 로그인 실패", error)
                    checkLogin.postValue(false)
                }
            }
        } else {
            Log.e(TAG, "카카오 로그인 실패", error)
            checkLogin.postValue(false)
        }
    }

    //카카오 로그인을 처리하는 메서드. 위 콜백을 사용해 id를 받을 수 있음.
    fun requestKakaoLogin(view: View) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(view.context)) {
            UserApiClient.instance.loginWithKakaoTalk(view.context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(view.context, callback = callback)
        }
    }

    fun requestHomeLogin(id: String, password: String) {
        //todo 로그인 체크 후 홈화면 이동.
        fireStore.collection("User")
            .document("home+${id}").get() //document찾기.
            .addOnSuccessListener { //해당되는 document의 Snapshot을 찾기.
                if (it.exists()) {
                    val value = it.getString("password")
                    Log.i("Value", value ?: "nulls")
                    if (value == password) { //비밀번호와 대조
                        saveInfo(it, id) // 파일에 아이디 비밀번호 저장.
                        checkLogin.postValue(true)
                    } else { //틀렸을 때
                        checkLogin.postValue(false)
                    }
                } else {
                    checkLogin.postValue(false)
                }
            }
    }

    //sharedPreferences를 사용해 로그인시 정보를 저장.
    private fun saveInfo(snapshot: DocumentSnapshot, id: String) {
        //빌더 패턴 사용으로 sharePreference 파일에 저장
        application.getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
            .putString("id", id)
            .putString("password", snapshot.getString("password"))
            .putString("loginRoute", snapshot.getString("loginRoute"))
            .putString("nickname", snapshot.getString("nickname"))
            .putString("image", snapshot.getString("image"))
            .apply()
    }

    // 오버로딩으로 userDTO로 sharedPreferences를 사용해 로그인시 정보를 저장.
    private fun saveInfo(userDTO: UserDTO, id: String) {
        //빌더 패턴 사용으로 sharePreference 파일에 저장
        application.getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
            .putString("id", id)
            .putString("password", userDTO.password)
            .putString("loginRoute", userDTO.loginRoute)
            .putString("nickname", userDTO.nickname)
            .putString("image", userDTO.image)
            .apply()
    }

    fun requestLogin(id: String, userDTO: UserDTO) {
        val docRef = fireStore.collection("User").document(id)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //id값이 있는지 확인
                val document = docRef.get().await()

                //아이디가 존재여부 확인
                if (document.exists()) {
                    // Document가 존재하는 경우 값들을 읽어와 저장.
                    saveInfo(document, id)
                } else {
                    // Document가 존재하지 않는 경우 데이터 추가.
                    docRef.set(userDTO).await()
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                }
                checkLogin.postValue(true)
            } catch (e: Exception) {
                checkLogin.postValue(false)
                Log.w(TAG, "Error writing document", e)
            }
        }
    }

}

