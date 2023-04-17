package com.incava.gangchuplace.model

data class User (
    val nickName : String,
    val image : String,
    val email : String,
    val loginRoute : String, // 로그인 경로 ex) 구글, 일반, 카카오
)