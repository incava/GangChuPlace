package com.incava.gangchuplace.model

data class UserDTO( // fireStore 저장 목적 객체
    val nickname : String,
    val image : String,
    val loginRoute : String, // 로그인 경로 ex) 구글, 일반, 카카오
    val password : String,
)
