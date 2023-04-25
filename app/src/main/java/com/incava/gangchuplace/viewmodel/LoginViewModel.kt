package com.incava.gangchuplace.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.firebase.firestore.DocumentSnapshot
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.viewmodel.repository.LoginRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 로그인에 사용되는 뷰모델
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var id = "" //로그인시 edit에 표시되는 id Text
    private var password = ""

    //repo객체.
    private var loginRepo = LoginRepo(application)

    //로그인이 잘 되었는지 확인하는 메서드.
    private val isCheckLogin: MutableLiveData<Boolean> get() = loginRepo.checkLogin


    //바인딩시 sync를 맞춰줄 set 메서드
    fun setId(txt: String) {
        id = txt
    }

    //바인딩시 sync를 맞춰줄 set 메서드
    fun setPass(txt: String) {
        password = txt
    }

    fun loginCheck() {
        loginRepo.requestHomeLogin(id, password)
    }

    fun kakaoLogin(view: View) {
        // 카카오톡으로 로그인 요청
        loginRepo.requestKakaoLogin(view)

    }

    //메서드로 간접적으로 observer를 붙여 사용
    fun checkLogin(owner: LifecycleOwner, observer : Observer<Boolean>) {
        isCheckLogin.observe(owner, observer)
    }






}