package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.view.main.MainActivity

/**
 * 로그인에 사용되는 뷰모델
 */
class LoginViewModel : ViewModel() {
    private var id = "" //로그인시 edit에 표시되는 id Text
    private var password = ""

    //바인딩시 sync를 맞춰줄 set 메서드
    fun setId(txt : String){
        id = txt
    }
    fun setPass(txt : String){
        password = txt
    }

    fun loginCheck(view : View){
        //todo 로그인 체크 후 홈화면 이동.
        Log.i("login","login완료!")
        view.findNavController().apply {
            graph.setStartDestination(R.id.baseContainerFragment)// nav graph의 처음 도착지를 변경
            navigate(R.id.action_loginFragment_to_baseContainerFragment) // home화면으로 이동.
        }

    }
    //회원가입 화면으로 이동
    fun moveSignup(view : View){
        //todo 로그인 체크 후 홈화면 이동.
        view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment) // home화면으로 이동.
    }

}