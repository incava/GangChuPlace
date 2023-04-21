package com.incava.gangchuplace.model

data class User (
    val nickname : String,
    val image : String,
    val id : String,
    val loginRoute : String, // 로그인 경로 ex) 구글, 일반, 카카오
    val password : String,
)