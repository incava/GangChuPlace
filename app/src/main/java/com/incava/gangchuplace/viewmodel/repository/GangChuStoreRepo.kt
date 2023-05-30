package com.incava.gangchuplace.viewmodel.repository

import android.Manifest
import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.QuerySnapshot
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.util.Common.fireStore
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * 파이어베이스에서 가게 정보를 가져 오는 Repo
 */

class GangChuStoreRepo(val application: Application) {

    lateinit var myLocation: LatLng

    // 모든 가게의 정보를 가지고 있는 List
    var storeList = MutableLiveData<MutableList<GangChuPreview>>()

    // 원하는 가게의 정보를 가지고 있는 List
    var storeFilterList = MutableLiveData<MutableList<GangChuPreview>>()

    // 찜한 가게의 정보를 가지고 있는 List
    var heartStoreList = MutableLiveData<MutableList<GangChuPreview>>()

    fun requestStoreList(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            storeList.postValue(loadStoreInfo(id))
        }

    }

    private suspend fun loadStoreInfo(id: String): MutableList<GangChuPreview> {
        // 나의 위치를 불러 오기.
        return CoroutineScope(Dispatchers.IO).async {
            getLocation()
            val snapshotStoreList = mutableListOf<GangChuPreview>()
            val start = System.currentTimeMillis()
            //가게 정보 불러 오기.
            val storeJob = loadStore()
            val friendJob = loadFriendId(id)
            val heartJob = loadIsHeartId(id)
            val storeDocuments = storeJob.await() ?: return@async snapshotStoreList
            val friendList = getList(friendJob.await() ?: return@async snapshotStoreList,"id")
            val heartList = getList(heartJob.await() ?: return@async snapshotStoreList,"title")


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
                val reviewDocuments =
                    loadReviewInfo(document.id).await() ?: return@async snapshotStoreList

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
                    friendRank,
                    getDistance(myLocation, LatLng(storePlace.mapx, storePlace.mapy))
                )
                snapshotStoreList.add(gangChuPreview)
            }
            Log.i("snapshotStoreList", snapshotStoreList.toString())
            //storeList.postValue(snapshotStoreList)
            Log.i("실행시간", (System.currentTimeMillis() - start).toString())
            snapshotStoreList
        }.await()
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
            data["mapx"].toString().toDouble(),
            data["mapy"].toString().toDouble(),
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
    fun loadIsHeartId(id: String): Deferred<QuerySnapshot?> {
        //makeLoop()
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val snapshot = fireStore.collection("User")
                    .document(id)
                    .collection("HeartStore")
                    .get()
                    .await()
                snapshot
            } catch (e: Exception) {
                null
            }
        }
    }

    //친구의 아이디 가져 오는 메서드
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
    private fun getList(snapshot: QuerySnapshot,index : String): MutableList<String> {
        val list = mutableListOf<String>()
        for (document in snapshot) {
            list.add(document.data[index].toString())
        }
        return list
    }
    //코루틴 비동기 작업이 되는지 확인 하려는 메서드.
//    fun makeLoop() {
//        val startTime = System.currentTimeMillis()
//        repeat(50000) {
//            repeat(50000) {
//
//            }
//        }
//        Log.i("실행시간1",(System.currentTimeMillis()-startTime).toString())
//    }

    private fun getLocation() {
        //만약 권한을 못 받았다면 서울시청으로 놓고 리턴.
        if (application.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            myLocation = LatLng(37.541, 126.986)
            return
        }
        //받아왔을 때,
        val manager = application.getSystemService(LOCATION_SERVICE) as LocationManager
        val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        myLocation = LatLng(location?.latitude ?: 37.541, location?.longitude ?: 126.986)
    }

    //둘 사이의 거리를 m로 변환 하는 메서드.
    fun getDistance(latLng1: LatLng, latLng2: LatLng): Int {
        // latLng의 위도
        val lat1 = latLng1.latitude
        val lon1 = latLng1.longitude
        val lat2 = latLng2.latitude
        val lon2 = latLng2.longitude
        val r = 6372.8 * 1000
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(lat1)) * cos(
            Math.toRadians(lat2)
        )
        val c = 2 * asin(sqrt(a))
        return (r * c).toInt()
    }

    // 가져온 가게 정보에서 원하는 값만 가져와 filter
    fun requestFilterSearchStore(query: String, id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val queryList = query.split(" ")//만약 여러 결과를 위해 " "가 들어갔을 경우 따로 생각해줘야함.
            var storeList = loadStoreInfo(id)
            //var storeList = loadStoreInfo(id).toMutableSet()
//            Log.i("sampleList",storeList.toString())
//            // 담을 빈 리스트
//            var list = mutableListOf<GangChuPreview>()

            // filter와 any 내장 함수를 통해, 간결하게 리팩토링
            val list = storeList.filter { item->
                queryList.any{queryItem->
                    item.storePlace.title.contains(queryItem) || item.storePlace.category.contains(queryItem) || item.storePlace.description.contains(queryItem)
                }
            }
//            storeList.forEach { item ->
//                // 원하는 검색 결과가 제목, 카테고리, 설명과 일치,포함하는 단어가 있을 경우를 찾음.
//                queryList.forEach { queryItem ->
//                    if (item.storePlace.title.contains(queryItem) || item.storePlace.category.contains(
//                            queryItem
//                        ) || item.storePlace.description.contains(queryItem)
//                    ) {
//                        list.add(item)
//                    }
//                }
//            }
            Log.i("list",list.toString())
            storeFilterList.postValue(list.toMutableList())
        }
    }

    fun requestMyHeartStore(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var storeList = loadStoreInfo(id).toMutableSet()
            //filter 함수를 통한 간결성 확인
            val list = storeList.filter {item->
                item.isHeart
            }
            heartStoreList.postValue(list.toMutableList())
        }
    }
}