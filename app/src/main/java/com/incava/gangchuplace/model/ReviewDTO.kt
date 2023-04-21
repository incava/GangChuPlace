package com.incava.gangchuplace.model

data class ReviewDTO(
    val timeStamp : String, // 작성 시간
    val rank : String, // 작성한 평점
    val body : String, // 작성한 글
    val store : String,
    val image : String,
)
