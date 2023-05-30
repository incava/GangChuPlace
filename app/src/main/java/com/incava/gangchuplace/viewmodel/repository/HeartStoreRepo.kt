package com.incava.gangchuplace.viewmodel.repository


import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.util.Common.fireStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HeartStoreRepo {

    //찜할 가게의 document를 생성하는 메서드.
    //withContext로 쓰레드 변경
    suspend fun insertHeartStore(id: String, item: GangChuPreview): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                fireStore.collection("User")
                    .document(id)
                    .collection("HeartStore")
                    .document(item.storePlace.title)
                    .set(item.storePlace)
                    .await()
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    //찜한 가게의 document를 제거하는 메서드.
    //withContext로 쓰레드 변경
    suspend fun deleteHeartStore(id: String, item: GangChuPreview): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                fireStore.collection("User")
                    .document(id)
                    .collection("HeartStore")
                    .document(item.storePlace.title)
                    .delete()
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}