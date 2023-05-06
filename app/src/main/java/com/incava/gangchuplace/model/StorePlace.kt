package com.incava.gangchuplace.model

import android.os.Parcelable

/**
 * 네이버 검색 지역 API를 받아오는 DTO
 */
data class StorePlace(
    var title : String = "",
    var category : String = "",
    var description : String = "",
    var address : String = "",
    var mapx : Double = 0.0,
    var mapy : Double = 0.0,
    var image : String = ""
)
