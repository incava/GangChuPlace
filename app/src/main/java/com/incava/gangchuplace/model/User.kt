package com.incava.gangchuplace.model

data class User (
    var nickname : String = "",
    var image : String = "",
    var email: String = "",
    var loginRoute : String ="", // 로그인 경로 ex) 구글, 일반, 카카오
    var password : String = ""
)