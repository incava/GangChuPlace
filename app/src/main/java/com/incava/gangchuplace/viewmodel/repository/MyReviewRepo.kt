package com.incava.gangchuplace.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.util.Common.fireStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MyReviewRepo {

    var reviewList = MutableLiveData<MutableList<MyReviewInfo>>(mutableListOf())

    fun loadMyReview(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //닉네임을 가져오는
            val getMyInfoJob = async {
                fireStore.collection("User")
                    .document(id)
                    .get()
                    .await()
            }
            val list = mutableListOf<MyReviewInfo>()
            val querySnapshot = fireStore.collection("User")
                .document(id)
                .collection("Review")
                .get()
                .await()
            //나의 info가 담긴 변수
            val myInfo = getMyInfoJob.await()
            for (document in querySnapshot) {
                val data = document.data
                list.add(getReviewInfo(data, myInfo.get("nickname").toString(),myInfo.get("image").toString()))
            }
            reviewList.postValue(list)
        }
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