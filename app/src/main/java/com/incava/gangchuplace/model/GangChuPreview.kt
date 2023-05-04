package com.incava.gangchuplace.model

data class GangChuPreview(
    var storePlace: StorePlace = StorePlace(), // 강추할 가게의 정보.
    val gangChuMember : Int = 0, // 강추한 친구의 맴버 리스트
    val rank : Double = 0.0,
    val isHeart : Boolean = false,
    val friendRank : Double = 0.0,
    val distance : Int = 0,
)
