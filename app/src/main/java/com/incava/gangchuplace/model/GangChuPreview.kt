package com.incava.gangchuplace.model

data class GangChuPreview(
    val storePlace: StorePlace, // 강추할 가게의 정보.
    val gangChuMember : String, // 강추한 친구의 맴버 리스트
    val rank : Double,
    val img : String, // 대표 이미지
    val isHeart : Boolean,
    val friendRank : Double
)
