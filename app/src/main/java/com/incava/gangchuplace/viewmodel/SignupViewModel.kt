package com.incava.gangchuplace.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.SignupCode
import com.incava.gangchuplace.viewmodel.repository.SignupRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    private val signupRepo by lazy { SignupRepo() }
    private var email = ""
    private var nickname = ""
    private var password = ""
    private var passwordConfirm = ""
    val isLoading = MutableLiveData(false)

    fun setId(txt: String) {
        email = txt
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
        CoroutineScope(Dispatchers.Main).launch {
            isLoading.postValue(true)
            val result = signupRepo.requestSignup(email, nickname, password, passwordConfirm)
            isLoading.postValue(false)
            if (result == SignupCode.SignupSuccess) {
                //회원 가입 성공
                Toast.makeText(view.context, "회원 가입 성공", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
            else{
                //회원 가입 실패
                Common.showDialog(view.context,"회원 가입 실패",result.comment)
            }
        }

    }

}