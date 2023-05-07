package com.incava.gangchuplace.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.CommonBindingAdapter
import com.incava.gangchuplace.model.UserDTO
import com.incava.gangchuplace.util.Common.fireStore
import com.incava.gangchuplace.util.Common.showDialog

class SignupViewModel : ViewModel() {

    private var id = ""
    private var nickname = ""
    private var password = ""
    private var passwordConfirm = ""
    private var loginRoute = "home"

    fun setId(txt: String) {
        id = txt
    }

    fun setNickname(txt: String) {
        nickname = txt
    }

    fun setPassword(txt: String) {
        password = txt
    }

    fun setPasswordConfirm(txt: String) {
        passwordConfirm = txt
    }

    fun signupCheck(view: View) {
        if (password!=passwordConfirm){
            showDialog(view.context, "회원가입 실패!", "아이디 또는 비밀번호를 다시 한번 확인해 주세요.")
            return
        }
        val user = UserDTO(nickname,"",loginRoute,password)
        fireStore.collection("User")
            .document("home+${id}")
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(view.context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
            .addOnFailureListener {
                showDialog(view.context, "회원가입 실패!", "아이디 또는 비밀번호를 다시 한번 확인해 주세요.")
            }
    }

}