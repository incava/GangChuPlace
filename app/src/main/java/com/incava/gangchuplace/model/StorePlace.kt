package com.incava.gangchuplace.model

import android.os.Parcelable

/**
 * 네이버 검색 지역 API를 받아오는 DTO
 */
data class StorePlace(
    var title : String, // item 변경때문에 var
    val category : String,
    val description : String,
    val address : String,
    val mapx : String,
    val mapy : String,
    var image : String
)
