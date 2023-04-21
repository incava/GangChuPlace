package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import com.incava.gangchuplace.adapter.Common.showDialog
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


/**
 * 친구의 리스트를 보여주는 ViewModel
 */
class FriendViewModel : ViewModel() {

    private var _friendList = MutableLiveData<MutableList<User>>()
    val friendList: MutableLiveData<MutableList<User>> get() = _friendList

    private var _requestFriendList = MutableLiveData<MutableList<User>>()
    val requestFriendList: MutableLiveData<MutableList<User>> get() = _requestFriendList

    private var _myRequestFriendList = MutableLiveData<MutableList<User>>()
    val myRequestFriendList: MutableLiveData<MutableList<User>> get() = _myRequestFriendList

    private var friendName: String = "" // 친구추가시 editText에 반응하여 넣어지는 값 변수

    init {// test용. 추후 Repo에 구현.
        //setvalue -> 메인 쓰레드로 즉각적 반응, postValue -> 백그라운드에서 작동, 조금늦음.
        //값을 많이 넣지 않아 setvalue로 설정.
        val a = mutableListOf<User>()
        repeat(6) {
            a.add(User("인기", "", "ingi1118@naver.com", "카카오톡", "123123"))
        }
        _friendList.value = a
        _requestFriendList.value = a
        _myRequestFriendList.value = a
    }

    fun loadFriend() {

    }

    fun loadRequestFriend() {
        Log.i("loadRequestFriend", "잘됩니다!")
    }

    fun loadMyRequestFriend() {
        Log.i("loadMyRequestFriend", "잘됩니다!")
    }

    fun setFriendName(editString: String) {
        friendName = editString
    }

    fun dismissView(view: View) {
        (view.findFragment() as DialogFragment).dismiss()
    }

    //요청한 사람의 이름이 있는지 확인
    private suspend fun nameCheck(): Int {
        return fireStore.collection("User")
            .whereEqualTo("nickname", friendName)
            .get()
            .await()
            .size()
    }

    fun requestFriend(view: View) {
        val user = getSharedPreference(view)
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                if (friendName == user.nickname  || nameCheck() == 0) { //자신의 닉네임과 같거나 같은 이름이 없을 경우.
                    CoroutineScope(Dispatchers.Main).launch {
                        showDialog(view, "친구 추가 실패", "등록된 닉네임이 없습니다.\n다시 한번 확인해 주세요.")
                    }
                }
                else{
                    dismissView(view)
                }
            }
        }
    }


}