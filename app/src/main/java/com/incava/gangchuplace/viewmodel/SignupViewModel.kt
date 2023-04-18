package com.incava.gangchuplace.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.incava.gangchuplace.R

class SignupViewModel : ViewModel() {
    val fireStore = FirebaseFirestore.getInstance()
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
        //todo loginRoute 추후 구현.
        val user = hashMapOf(
            "nickname" to nickname,
            "img" to "",
            "loginRoute" to loginRoute, // 기본은 home 카카오는 kakao, 구글은 google
            "password" to password
        )
        fireStore.collection("User")
            .document("home+${id}")
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(view.context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
            .addOnFailureListener {
                Toast.makeText(view.context, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
    }

}