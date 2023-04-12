package com.incava.gangchuplace.model

data class ReviewInfo(
    val name : String, //작성한 사람의 닉네임
    val img : String, // 작성한 사람의 이미지 경로.
    val timeStamp : String, // 작성 시간
    val rank : String, // 작성한 평점
    val body : String, // 작성한 글
)
