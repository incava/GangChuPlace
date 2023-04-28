package com.incava.gangchuplace.viewmodel.repository

import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GangChuStoreRepo {

    val storeList = mutableListOf<StorePlace>()
    fun loadStoreInfo(id : String) {
        val snapshotStoreList = mutableListOf<GangChuPreview>()
        val gangChuMember : String = "" // 강추한 친구의 맴버 리스트
        val rank : Double = 0.0
        val img : String = "" // 대표 이미지
        val isHeart : Boolean = false
        val friendRank : Double = 0.0

        CoroutineScope(Dispatchers.IO).launch {
            //가게 정보 불러 오기.
            val storeDocuments = loadStore()
            //친구 목록 가져 오기.
            val friendList = loadFriendId(id)

            for (document in storeDocuments) {
                val data = document.data
            }
        }
    }

    //가게에 저장된 정보를 가져 오는 메서드.
    private suspend fun loadStore() : QuerySnapshot{
        val storeRef = fireStore.collection("Store")
       return storeRef.get().await()
    }

    // 유저의 친구 목록을 가져 오는 메서드.
    private suspend fun loadFriendId(id:String) : MutableList<String> {
        val friendList = mutableListOf<String>()

        val friendDocument = fireStore.collection("User")
            .document(id)
            .collection("Friend")
            .get()
            .await()
        // 모두 가져온 후, id를 List에 넣은 후 return
        for(document in friendDocument){
            friendList.add(document.data["id"].toString())
        }
        return friendList
    }


}