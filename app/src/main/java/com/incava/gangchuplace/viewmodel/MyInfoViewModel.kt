package com.incava.gangchuplace.viewmodel

import android.provider.Settings.Global
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.application.GlobalApplication
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.util.Prefs
import com.incava.gangchuplace.view.main.MainActivity

class MyInfoViewModel : ViewModel() {

    //내 정보를 담고 있는 ViewModel
    private var _myInfo = MutableLiveData<User>()
    val myInfo: MutableLiveData<User> get() = _myInfo

    init {
        loadMyInfo()
    }

    // 나의 정보를 로드.
    private fun loadMyInfo(){
        _myInfo.value = GlobalApplication.prefs.getSharedPreference()
    }

    fun logout(view: View) {
        AlertDialog.Builder(view.context)
            .setMessage("로그아웃을 하시겠습니까?")
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("확인") { _, _ ->
                // 로그아웃 후, 다시 ViewModel을 생성하기
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