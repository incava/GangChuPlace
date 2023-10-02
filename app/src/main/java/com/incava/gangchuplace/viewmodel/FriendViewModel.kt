package com.incava.gangchuplace.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.Common.fireStore
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.util.Common.showDialog
import com.incava.gangchuplace.util.FriendCode
import com.incava.gangchuplace.viewmodel.repository.FriendRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


/**
 * 친구의 리스트를 보여주는 ViewModel
 */
class FriendViewModel(application: Application) : AndroidViewModel(application) {

    private val friendRepo by lazy { FriendRepo() }

    private var _friendList = MutableLiveData<MutableList<User>>(mutableListOf())
    val friendList: MutableLiveData<MutableList<User>> get() = _friendList

    private var _requestedFriendList = MutableLiveData<MutableList<User>>(mutableListOf())
    val requestedFriendList: MutableLiveData<MutableList<User>> get() = _requestedFriendList

    private var _myRequestFriendList = MutableLiveData<MutableList<User>>(mutableListOf())
    val myRequestFriendList: MutableLiveData<MutableList<User>> get() = _myRequestFriendList

    private var friendName: String = "" // 친구추가시 editText에 반응하여 넣어지는 값 변수

    init {
        loadFriendInfo()
    }

    //모든 정보를 load
    private fun loadFriendInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("loadFriedInfo","실행중")
            val id = getUniqueId()
            _friendList.postValue(friendRepo.loadUserInfo(id, "Friend"))
            _requestedFriendList.postValue(friendRepo.loadUserInfo(id, "RequestedFriend"))
            _myRequestFriendList.postValue(friendRepo.loadUserInfo(id, "RequestFriend"))
            Log.i("friendRepo",friendRepo.loadUserInfo(id, "RequestFriend").toString())
            Log.i("friendRepo2",myRequestFriendList.value.toString())
        }
    }


    //친구 요청 메서드.
    fun requestFriend(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            val user = getSharedPreference(getApplication())
            //요청 결과
            val resultCode = friendRepo.requestFriend(
                friendName = friendName,
                nickname = user.nickname,
                id = getUniqueId()
            )
            when (resultCode) {
                FriendCode.RequestSuccess -> {
                    dismissView(view)
                    Toast.makeText(view.context, "친구 추가 요청 완료!", Toast.LENGTH_SHORT).show()
                    loadFriendInfo()
                }

                FriendCode.NotExistFail -> showDialog(view.context, "친구 추가 실패", resultCode.comment)
                FriendCode.AlreadyRequestFail -> showDialog(
                    view.context,
                    "이미 요청한 상태",
                    "이미 친구 요청한 상태입니다.\n다시 한번 확인해 주세요."
                )

                FriendCode.NetworkFail -> Toast.makeText(
                    view.context,
                    "알 수 없는 원인으로 실패 하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //요청한 친구 정보를 제거 하는 메서드.
    fun removeRequestedFriend(view: View, user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            if (friendRepo.deleteRequestedFriend(
                    getUniqueId(),
                    "${user.loginRoute}+${user.email}"
                )
            ) {
                Toast.makeText(view.context, "요청이 성공하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "요청이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
            loadFriendInfo()
        }
    }

    //요청한 친구 정보를 제거 하는 메서드.
    fun allowRequestedFriend(view: View, user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            if (friendRepo.allowRequestedFriend(
                    getUniqueId(),
                    "${user.loginRoute}+${user.email}"
                )
            ) {
                Toast.makeText(view.context, "요청이 성공하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "요청이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
            loadFriendInfo()
        }
    }

    //요청된 친구 정보를 제거 하는 메서드.
    fun removeRequestFriend(view: View, user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            if (friendRepo.deleteRequestFriend(getUniqueId(), "${user.loginRoute}+${user.email}")) {
                Toast.makeText(view.context, "요청이 성공하였습니다.", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(view.context, "요청이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            loadFriendInfo()
        }
    }

    //친구 정보를 제거 하는 메서드.
    fun removeFriend(view: View, user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            if (friendRepo.deleteFriend(getUniqueId(), "${user.loginRoute}+${user.email}")) {
                Toast.makeText(view.context, "요청이 성공하였습니다.", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(view.context, "요청이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            loadFriendInfo()
        }
    }

    fun setFriendName(editString: String) {
        friendName = editString
    }

    fun dismissView(view: View) {
        (view.findFragment() as DialogFragment).dismiss()
    }

    //UniqueId 불러옴
    fun getUniqueId(): String {
        val user = getSharedPreference(getApplication())
        return "${user.loginRoute}+${user.email}"
    }


}