package com.incava.gangchuplace.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.util.Common.fireStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ReviewStoreRepo {

    // 해당 가게의 리뷰를 가지고 있는 List
    var storeReviewList = MutableLiveData<MutableList<MyReviewInfo>>()

    fun loadStoreReviewList(storeTitle: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //가게 리뷰를 담을 빈 배열
            var list = mutableListOf<MyReviewInfo>()
            //가게의 리뷰를 담고 있는 스냅샷
            val querySnapshot = fireStore.collection("Store")
                .document(storeTitle)
                .collection("Review")
                .get()
                .await()
            for (document in querySnapshot.documents) {
                val data = document.data
                if (data != null) {
                    list.add(getReviewInfo(data,getNickName(document.id),getProfile(document.id)))
                }
            }
            Log.i("listItems",list.toString())
            storeReviewList.postValue(list)
        }
    }

    //id로 닉네임을 찾는 메서드.
    suspend fun getNickName(id: String) : String{
        return CoroutineScope(Dispatchers.IO).async {
            val job = fireStore.collection("User")
                .document(id)
                .get()
                .await()
            (job.get("nickname") ?: "탈퇴한 회원").toString()
        }.await()
    }

    //id로 프로필 이미지를 찾는 메서드.
    suspend fun getProfile(id: String) : String{
        return CoroutineScope(Dispatchers.IO).async {
            val job = fireStore.collection("User")
                .document(id)
                .get()
                .await()
            (job.get("image") ?: "").toString()
        }.await()
    }
    private fun getReviewInfo(data: Map<String, Any>, name: String,profile: String): MyReviewInfo {
        return MyReviewInfo(
            profile = profile,
            name = name,
            image = data["image"].toString(),
            timeStamp = data["timeStamp"].toString(),
            rank = data["rank"].toString(),
            body = data["body"].toString(),
            store = data["store"].toString(),
        )
    }


}