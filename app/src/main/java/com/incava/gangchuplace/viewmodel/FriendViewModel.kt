package com.incava.gangchuplace.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.model.User


/**
 * 친구의 리스트를 보여주는 ViewModel
 */
class FriendViewModel : ViewModel() {

    private var _friendList = MutableLiveData<MutableList<User>>()
    val friendList : MutableLiveData<MutableList<User>> get() = _friendList

    private var _requestFriendList = MutableLiveData<MutableList<User>>()
    val requestFriendList : MutableLiveData<MutableList<User>> get() = _requestFriendList

    private var _myRequestFriendList = MutableLiveData<MutableList<User>>()
    val myRequestFriendList : MutableLiveData<MutableList<User>> get() = _myRequestFriendList

    init {// test용. 추후 Repo에 구현.
        //setvalue -> 메인 쓰레드로 즉각적 반응, postValue -> 백그라운드에서 작동, 조금늦음.
        //값을 많이 넣지 않아 setvalue로 설정.
        val a = mutableListOf<User>()
        repeat(6){
            a.add(User("인기","","ingi1118@naver.com","카카오톡"))
        }
        _friendList.value = a
        _requestFriendList.value = a
        _myRequestFriendList.value = a
    }

    fun loadFriend(){
        Log.i("loadFriend","잘됩니다!")
    }

    fun loadRequestFriend(){
        Log.i("loadRequestFriend","잘됩니다!")
    }

    fun loadMyRequestFriend(){
        Log.i("loadMyRequestFriend","잘됩니다!")
    }



}