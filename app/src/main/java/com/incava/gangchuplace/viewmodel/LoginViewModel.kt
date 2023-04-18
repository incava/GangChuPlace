package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.adapter.Common.showDialog

/**
 * 로그인에 사용되는 뷰모델
 */
class LoginViewModel : ViewModel() {
    private var id = "" //로그인시 edit에 표시되는 id Text
    private var password = ""

    //바인딩시 sync를 맞춰줄 set 메서드
    fun setId(txt: String) {
        id = txt
    }

    fun setPass(txt: String) {
        password = txt
    }

    //홈화면으로 이동시키는 메서드
    private fun moveHome(view: View) {
        view.findNavController().apply {
            graph.setStartDestination(R.id.baseContainerFragment)// nav graph의 처음 도착지를 변경
            navigate(R.id.action_loginFragment_to_baseContainerFragment) // home화면으로 이동.
        }
    }


    fun loginCheck(view: View) {
        //todo 로그인 체크 후 홈화면 이동.
        fireStore.collection("User")
            .document("home+${id}").get() //document찾기.
            .addOnSuccessListener { //해당되는 document의 Snapshot을 찾기.
                if (it.exists()) {
                    val value = it.getString("password")
                    Log.i("Value", value ?: "nulls")
                    if (value == password) { //비밀번호와 대조
                        moveHome(view) //맞을 때 홈으로 이동.
                    }else{ //틀렸을 때
                        showDialog(view,"로그인 실패","비밀번호가 다릅니다.\n다시한번 확인해 주세요.")
                    }

                } else {
                    showDialog(view, "로그인 실패", "아이디가 없습니다.")
                    Log.i("Value", "empty!")
                }
            }
    }

    //회원가입 화면으로 이동
    fun moveSignup(view: View) {
        //todo 로그인 체크 후 홈화면 이동.
        view.findNavController()
            .navigate(R.id.action_loginFragment_to_signupFragment) // home화면으로 이동.
    }

}