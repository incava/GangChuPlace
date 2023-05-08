package com.incava.gangchuplace.viewmodel.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.util.Common.fireStore
import com.incava.gangchuplace.util.FriendCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FriendRepo {

    //나의 친구들을 불러오는 메서드 - query에 맞는 정보
    suspend fun loadUserInfo(id: String, query: String): MutableList<User> {
        return CoroutineScope(Dispatchers.IO).async {
            val list = mutableListOf<User>()
            try {
                // query에 있는 친구 이름 가져 오기
                val querySnapshot = fireStore.collection("User")
                    .document(id)
                    .collection(query)
                    .get()
                    .await()
                // 친구들의 아이디 정보를 User에 담음.
                for (document in querySnapshot.documents) {
                    val friendId = document.get("id").toString()
                    val friendInfo = fireStore.collection("User")
                        .document(friendId)
                        .get()
                        .await()
                    Log.i("friendInfo", friendInfo.toString())
                    list.add(getUserList(friendInfo))
                }
                list
            } catch (e: Exception) {
                list
            }
        }.await()
    }

    //요청한 사람의 이름이 있는지 확인
    private suspend fun nameCheck(friendName: String): QuerySnapshot {
        return fireStore.collection("User")
            .whereEqualTo("nickname", friendName)
            .get()
            .await()
    }

    //친구 요청 메서드
    suspend fun requestFriend(friendName: String, nickname: String, id: String): FriendCode {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                var friendId = nameCheck(friendName)
                //자신의 닉네임과 같거나 같은 이름이 없을 경우.
                if (friendName == nickname || friendId.documents.isEmpty()) {
                    return@async FriendCode.NotExistFail
                } else if (requestDuplicateCheck(
                        friendId.documents[0].id,
                        id
                    ).documents.isNotEmpty()
                ) { //만약 이미 요청한 아이디가 있다면
                    return@async FriendCode.AlreadyRequestFail
                } else { // 친구할 닉네임을 찾았을 때
                    val deffereds = listOf( // 두개의 요청이 완료 되기 위해 list로 묶어서 await시키기.
                        async { uploadRequestFriend(friendId.documents[0].id, id) },
                        async { uploadRequestedFriend(friendId.documents[0].id, id) }
                    )
                    deffereds.awaitAll()
                    //모든 요청이 끝났을 때 이상이 없다면?
                    return@async FriendCode.RequestSuccess
                }
            } catch (e: Exception) {
                return@async FriendCode.NetworkFail
            }
        }.await()
    }

    //친구 요청 메서드
    private suspend fun uploadRequestFriend(friendId: String, myId: String) {
        fireStore.collection("User")
            .document(myId)
            .collection("RequestFriend")
            .add(mapOf("id" to friendId))
            .await()
    }

    //친구에게 요청을 보내는 메서드
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

    //document To User 변환 메서드
    private fun getUserList(documentSnapshot: DocumentSnapshot): User {
        val image = documentSnapshot.get("image").toString()
        val loginRoute = documentSnapshot.get("loginRoute").toString()
        val nickname = documentSnapshot.get("nickname").toString()
        val password = documentSnapshot.get("password").toString()
        val email = documentSnapshot.id.split("+")[1]
        return User(
            nickname = nickname,
            image = image,
            email = email,
            loginRoute = loginRoute,
            password = password
        )
    }

    // User 지우는 메서드
    suspend fun deleteUser(id: String, friendId: String, query: String): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val queryRef = fireStore.collection("User").document(id).collection(query)
                val querySnapshot = queryRef.whereEqualTo("id", friendId)
                    .get()
                    .await()
                if (!querySnapshot.isEmpty) {
                    queryRef.document(querySnapshot.documents[0].id)
                        .delete()
                        .await()
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    // User 서로 친구 메서드
    private suspend fun allowUser(
        id: String,
        friendId: String,
        query: String
    ): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val querySnapshot = fireStore.collection("User")
                    .document(id).collection(query)
                    .whereEqualTo("id", friendId)
                    .get()
                    .await()
                // Friend에 문서를 옮김.
                if (!querySnapshot.isEmpty) {
                    fireStore.collection("User")
                        .document(id)
                        .collection("Friend")
                        .document()
                        .set(querySnapshot.documents[0].data!!)
                        .await()

                    //문서 삭제
                    fireStore.collection("User")
                        .document(id)
                        .collection(query)
                        .document(querySnapshot.documents[0].id).delete()
                        .await()
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun allowRequestedFriend(id: String, friendId: String): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val result1 = allowUser(id, friendId, "RequestedFriend")
                val result2 = allowUser(friendId, id, "RequestFriend")
                (result1 && result2)
            } catch (e: Exception) {
                false
            }
        }
    }


    //내가 친구 요청 받은 친구 Delete
    suspend fun deleteRequestedFriend(id: String, friendId: String): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val result1 = deleteUser(id, friendId, "RequestedFriend")
                val result2 = deleteUser(friendId, id, "RequestFriend")
                (result1 && result2)
            } catch (e: Exception) {
                false
            }
        }
    }

    //내가 친구 요청 보낸 친구 Delete
    suspend fun deleteRequestFriend(id: String, friendId: String): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val result1 = deleteUser(id, friendId, "RequestFriend")
                val result2 = deleteUser(friendId, id, "RequestedFriend")
                (result1 && result2)
            } catch (e: Exception) {
                false
            }
        }
    }

    //내가 친구 요청 보낸 친구 Delete
    suspend fun deleteFriend(id: String, friendId: String): Boolean {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                val result1 = deleteUser(id, friendId, "Friend")
                val result2 = deleteUser(friendId, id, "Friend")
                (result1 && result2)
            } catch (e: Exception) {
                false
            }
        }
    }


}