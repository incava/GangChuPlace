package com.incava.gangchuplace.model

/**
 * 내가 작성한 리뷰 정보
 */
class MyReviewInfo (
    val profile : String, //작성한 사람의 프로필
    val name : String, //작성한 사람의 닉네임
    val image : String, // 작성한 사람의 이미지 경로.
    val timeStamp : String, // 작성 시간
    val rank : String, // 작성한 평점
    val body : String, // 작성한 글
    val store : String, // 작성한 가게
    )