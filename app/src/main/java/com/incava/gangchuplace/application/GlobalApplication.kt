package com.incava.gangchuplace.application

import android.annotation.SuppressLint
import android.app.Application
import com.incava.gangchuplace.util.Prefs
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들
        prefs = Prefs(applicationContext)
        // Kakao SDK 초기화
        KakaoSdk.init(this, "1f2c1e9d08a41548513d2460cf27376c")
    }
}