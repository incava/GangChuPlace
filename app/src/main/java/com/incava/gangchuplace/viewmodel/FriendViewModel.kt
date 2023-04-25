package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import com.incava.gangchuplace.adapter.Common.showDialog
import com.incava.gangchuplace.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    //요청한 친구정보를 불러오는 메서드.
    fun loadRequestFriend(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            var query = loadFriendId(view, "RequestFriend")

        }
    }

    //load할 친구의 id 데이터를 가져오기 위한 메서드. collection에는 collection에 들어갈 문자열 사용.
    private suspend fun loadFriendId(view: View, collection: String): MutableList<String> {
        var friendIds = mutableListOf<String>()
        var user = getSharedPreference(view)
        val query = fireStore.collection("User")
            .document("${user.loginRoute}+${user.id}")
            .collection(collection)
            .get()
            .await()
        for (field in query.documents) {
            friendIds.add(field.data?.get("id").toString())
        }
        return friendIds
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
    private suspend fun nameCheck(): QuerySnapshot {
        return fireStore.collection("User")
            .whereEqualTo("nickname", friendName)
            .get()
            .await()
    }

    fun requestFriend(view: View) {
        try {
            val user = getSharedPreference(view)
            CoroutineScope(Dispatchers.IO).launch {
                var friendId = nameCheck()
                //자신의 닉네임과 같거나 같은 이름이 없을 경우.
                if (friendName == user.nickname || friendId.documents.isEmpty()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        showDialog(view.context, "친구 추가 실패", "등록된 닉네임이 없습니다.\n다시 한번 확인해 주세요.")
                    }
                } else if (requestDuplicateCheck(
                        friendId.documents[0].id,
                        "${user.loginRoute}+${user.id}"
                    ).documents.isNotEmpty()
                ) {
                    //만약 이미 요청한 아이디가 있다면
                    CoroutineScope(Dispatchers.Main).launch {
                        showDialog(view.context, "이미 요청한 상태", "이미 친구 요청한 상태입니다.\n다시 한번 확인해 주세요.")
                    }
                } else { // 친구할 닉네임을 찾았을 때
                    val deffereds = listOf( // 두개의 요청이 완료 되기 위해 list로 묶어서 await시키기.
                        async {
                            uploadRequestFriend(
                                friendId.documents[0].id,
                                "${user.loginRoute}+${user.id}"
                            )
                        },
                        async {
                            uploadRequestedFriend(
                                friendId.documents[0].id,
                                "${user.loginRoute}+${user.id}"
                            )
                        }
                    )
                    deffereds.awaitAll()
                    //모든 요청이 끝났을 때 이상이 없다면?
                    CoroutineScope(Dispatchers.Main).launch {
                        dismissView(view)
                        Toast.makeText(view.context, "친구 추가 요청 완료!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(view.context, "알 수 없는 원인으로 실패 하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun uploadRequestFriend(friendId: String, myId: String) {
        fireStore.collection("User")
            .document(myId)
            .collection("RequestFriend")
            .add(mapOf("id" to friendId))
            .await()
    }

    private suspend fun uploadRequestedFriend(friendId: String, myId: String) {
        fireStore.collection("User")
            .document(friendId)
            .collection("RequestedFriend")
            .add(mapOf("id" to myId))
            .await()
    }

    //요청을 이미 했는지 확인.
    private suspend fun requestDuplicateCheck(friendId: String, myId: String): QuerySnapshot {
        return fireStore.collection("User")
            .document(myId)
            .collection("RequestFriend")
            .whereEqualTo("id", friendId)
            .get()
            .await()
    }


}