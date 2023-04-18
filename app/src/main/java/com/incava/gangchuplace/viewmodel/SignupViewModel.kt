package com.incava.gangchuplace.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R

class SignupViewModel : ViewModel() {
    private var id = ""
    private var nickname = ""
    private var password = ""
    private var passwordConfirm = ""

    fun setId(txt: String){
        id = txt
    }

    fun setNickname(txt: String){
        nickname = txt
    }

    fun setPassword(txt: String){
        password = txt
    }

    fun setPasswordConfirm(txt: String){
        passwordConfirm = txt
    }

    fun signupCheck(view : View){
        view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
    }

}