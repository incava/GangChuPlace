package com.incava.gangchuplace.util

import android.util.Log

object Regex{

    private val regexEmail = Regex("^[_a-zA-Z0-9]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$")// 문자 또는 숫자 _만 가능.
    private val regexPassword = Regex("^[A-Za-z0-9]{6,15}$") //문자 또는 숫자로만, 6이상 15이하로.
    private val regexNickname = Regex("^[A-Za-z0-9]{2,8}$") // 문자 또는 숫자로만, 2이상 8이하로.
    fun regexCheck(cmpString:String,form:String):Boolean{
        Log.i("email",cmpString.matches(regexEmail).toString())
        Log.i("pass",cmpString.matches(regexPassword).toString())
        Log.i("regexNickname",cmpString.matches(regexNickname).toString())
        return when(form){
            "email" -> cmpString.matches(regexEmail)
            "password" -> cmpString.matches(regexPassword)
            "nickname" -> cmpString.matches(regexNickname)
            else -> false
        }
    }
}