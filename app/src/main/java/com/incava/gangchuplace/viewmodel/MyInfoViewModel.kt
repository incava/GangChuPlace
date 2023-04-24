package com.incava.gangchuplace.viewmodel

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.MainActivity

class MyInfoViewModel : ViewModel() {

    //내 정보를 담고 있는 ViewModel
    private var _myInfo = MutableLiveData<User>()
    val myInfo: MutableLiveData<User> get() = _myInfo

    init {
        _myInfo.value = User("인기", "", "ingi111111@naver.com", "카카오", "123123")
    }

    // 비밀번호 text와 바인딩된 변수
    private var password = ""

    //password가 변경될 때마다 함수 호출.
    fun getPass(editText: String) {
        password = editText
    }

    fun saveInfo(view: View) {
        Toast.makeText(view.context, "저장 완료!", Toast.LENGTH_SHORT).show()
    }


    //비밀번호가 맞는지 확인하는 메서드.
    fun confirmPass(view: View) {
        var a = true // 임시 변수
        if (a) { // todo repo
            //비밀번호를 변경하는 로직.
        } else {
            AlertDialog.Builder(view.context)
                .setMessage("비밀번호가 다릅니다.\n다시 한번 확인해 주세요.")
                .setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    fun logout(view: View) {
        AlertDialog.Builder(view.context)
            .setMessage("로그아웃을 하시겠습니까?")
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("확인") { _, _ ->
                (view.context as MainActivity).findNavController(R.id.main_nav_host)
                    .navigate(R.id.action_baseContainerFragment_to_loginFragment)
            }.show()
    }


    fun withdrawalMember(view: View) {
        AlertDialog.Builder(view.context)
            .setMessage("정말 탈퇴하시겠습니까?")
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("확인") { dialog, _ ->
                AlertDialog.Builder(view.context)
                    .setMessage("회원 탈퇴가 성공하였습니다.\n확인을 누르시면 로그인 화면으로 이동합니다.")
                    .setPositiveButton("확인") { _, _ ->
                        (view.context as MainActivity).findNavController(R.id.main_nav_host)
                            .navigate(R.id.action_baseContainerFragment_to_loginFragment)
                    }.show()
            }.show()
    }


}