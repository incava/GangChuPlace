package com.incava.gangchuplace.viewmodel.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.adapter.Common.fireStore
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import kotlin.math.roundToInt

class GangChuStoreRepo {

    var storeList = MutableLiveData<MutableList<GangChuPreview>>()

    fun loadStoreInfo(id: String) {
        val snapshotStoreList = mutableListOf<GangChuPreview>()
        CoroutineScope(Dispatchers.IO).launch {
            val start = System.currentTimeMillis()
            //가게 정보 불러 오기.
            val storeJob = loadStore()
            val friendJob = loadFriendId(id)
            val heartJob= loadIsHeartId(id)
            val storeDocuments = storeJob.await()?:return@launch
            val friendList = getList(friendJob.await()?:return@launch)
            val heartList = getList(heartJob.await()?:return@launch)


            //가게 정보 마다 Review 가져 오기 위한 로직
            for (document in storeDocuments) {
                //document의 field를 모두 가져와 storePlace로 반환.
                val storePlace = loadStoreInfo(document.data)

                // Rank를 저장하는 List
                var rankList = mutableListOf<Double>()

                // 친구의 Rank를 저장하는 List
                var friendRankList = mutableListOf<Double>()

                // 강추한 친구들을 저장하는 리스트
                var gangChuFriendMemberList = mutableListOf<String>()

                // document의 Review컬렉션 정보를 가져와 리턴.
                val reviewDocuments = loadReviewInfo(document.id).await()?:return@launch

                // review 마다 rank를 가져 오기 위한 로직.
                for (review in reviewDocuments) {
                    var rank = (review["rank"] ?: 0.0).toString().toDouble()
                    if (friendList.contains(review.id)) {
                        // 친구의 아이디를 가져옴.
                        gangChuFriendMemberList.add(loadNickname(review.id))
                        // 친구의 rank를 조사 후, 배열에 추가.
                        friendRankList.add(rank)
                    }
                    rankList.add(rank)
                }
                // 하나의 가게 정보를 가져온 뒤

                // 강추 멤버는 경우에 따른 문자열
                val gangChuMember = gangChuFriendMemberList.size
                val rank = getEverage(rankList)
                val friendRank = getEverage((friendRankList))

                //모든 강추아이템을 종합적으로 가져와 객체로 만듦.
                var gangChuPreview = GangChuPreview(
                    storePlace,
                    gangChuMember,
                    rank,
                    heartList.contains(document.id),
                    friendRank
                )
                snapshotStoreList.add(gangChuPreview)
            }
            Log.i("snapshotStoreList", snapshotStoreList.toString())
            storeList.postValue(snapshotStoreList)
            Log.i("실행시간",(System.currentTimeMillis()-start).toString())
        }
    }

    //평균 값을 소수점1번째 반올림 반환 메서드
    fun getEverage(list: List<Double>): Double {
        var sum = 0.0
        if (list.isEmpty()) return 0.0
        for (num in list) {
            sum += num
        }
        return (sum * 10).div(list.size).roundToInt().toDouble() / 10
    }

    suspend fun loadImage(id: String) = fireStore.collection("User")
        .document(id)
        .get()
        .await()
        .get("image").toString()

    private suspend fun loadNickname(userId: String) =
        fireStore.collection("User")
            .document(userId)
            .get()
            .await()
            .get("nickname")
            .toString()


    // StorePlace 객체를 추출 하는 메서드
    private fun loadStoreInfo(data: Map<String, Any>): StorePlace {
        return StorePlace(
            data["title"].toString(),
            data["category"].toString(),
            data["description"].toString(),
            data["address"].toString(),
            data["mapx"].toString(),
            data["mapy"].toString(),
            data["image"].toString()
        )
    }

    //가게에 저장된 정보를 가져 오는 메서드.
    private fun loadStore(): Deferred<QuerySnapshot?> {
        //makeLoop()
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val snapshot = fireStore.collection("Store")
                    .get()
                    .await()
                snapshot
            } catch (e: Exception) {
                null
            }
        }
    }

    // 유저의 찜 목록을 가져 오는 메서드.
    private fun loadIsHeartId(id: String): Deferred<QuerySnapshot?> {
        //makeLoop()
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val snapshot = fireStore.collection("User")
                    .document(id)
                    .collection("isHeart")
                    .get()
                    .await()
                snapshot
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun loadFriendId(id: String): Deferred<QuerySnapshot?> {
        //makeLoop()
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val snapshot = fireStore.collection("User")
                    .document(id)
                    .collection("Friend")
                    .get()
                    .await()
                snapshot
            } catch (e: Exception) {
                null
            }
        }
    }

    //가게에 저장된 리뷰들을 가져 오는 메서드.
    private fun loadReviewInfo(storeId: String): Deferred<QuerySnapshot?> {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val snapshot = fireStore.collection("Store")
                    .document(storeId)
                    .collection("Review")
                    .get()
                    .await()
                snapshot
            } catch (e: Exception) {
                null
            }
        }
    }


// 유저의 친구 목록을 가져 오는 메서드.

    //받은 쿼리스냅샷을 리스트로 바꿔주는 메서드.
    private fun getList(snapshot: QuerySnapshot): MutableList<String> {
        val list = mutableListOf<String>()
        for (document in snapshot) {
            list.add(document.data["id"].toString())
        }
        return list
    }

//    fun makeLoop() {
//        val startTime = System.currentTimeMillis()
//        repeat(50000) {
//            repeat(50000) {
//
//            }
//        }
//        Log.i("실행시간1",(System.currentTimeMillis()-startTime).toString())
//    }


}