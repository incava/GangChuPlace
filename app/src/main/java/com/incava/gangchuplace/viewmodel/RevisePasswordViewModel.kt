package com.incava.gangchuplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incava.gangchuplace.application.GlobalApplication
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.Regex
import com.incava.gangchuplace.util.TransformPasswordCode

class RevisePasswordViewModel : ViewModel() {

    var currentPass = ""
    var newPass = ""
    var confirmPass = ""
    var confirmResult = MutableLiveData<TransformPasswordCode>()

    fun setTextCurrentPass(pass: String) {
        currentPass = pass
    }

    fun setTextNewPass(pass: String) {
        newPass = pass
    }

    fun setTextConfirmPass(pass: String) {
        confirmPass = pass
    }

    fun transformPassword() {
        // 변경해도 되는지 판단.
        if (currentPass != GlobalApplication.prefs.getSharedPreference().password) {
            confirmResult.postValue(TransformPasswordCode.NotMatchCurrentPass)
            return
        } else if (newPass == GlobalApplication.prefs.getSharedPreference().password) {
            confirmResult.postValue(TransformPasswordCode.EqualCurrentAndNewPass)
            return
        } else if (newPass != confirmPass) {
            confirmResult.postValue(TransformPasswordCode.NotMatchPassConfirm)
            return
        } else if (!Regex.regexCheck(newPass, "password")) {
            confirmResult.postValue(TransformPasswordCode.NotRegexNewPass)
            return
        }
        // 모든 체크 후, 업로드 준비
        val updatedData = hashMapOf(
            "password" to newPass,
        )
        val id = GlobalApplication.prefs.getSharedPreference()
            .run { "${loginRoute}+${email}" }
        Common.fireStore.collection("User")
            .document(id)
            .update(updatedData as Map<String, Any>)
            .addOnSuccessListener {
                confirmResult.postValue(TransformPasswordCode.TransformPassSuccess)
            }
            .addOnFailureListener {
                confirmResult.postValue(TransformPasswordCode.TransformPassFail)
            }
    }
}