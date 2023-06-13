package com.incava.gangchuplace.util

import android.content.Context
import com.incava.gangchuplace.model.User

/**
 * 6월 추가. SingleActivity이기 때문에 계속 sharedPreference를 전역에서 사용해 메모리 릭이 발생해도 괜찮으므로
 * 전역으로 사용하기 위해 편하게 만든 class
 */
class Prefs(val context: Context) {

    // sharedPreference 파일 가져올 수 있는 메서드
    fun getSharedPreference(): User {
        //파일이 없으면 빈 값으로 리턴.
        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            ?: return (User("", "", "", "", ""))

        return sharedPreferences.run {
            User(
                getString("nickname", "null") ?: "",
                getString("image", "null") ?: "",
                getString("id", "null") ?: "",
                getString("loginRoute", "null") ?: "",
                getString("password", "null") ?: "",
            )
        }
    }

    // 맵으로 원하는 만큼 집어넣도록 편하게 만든 메서드
    fun setSharedPreference(map: Map<String, String>) {
        val edit = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
        map.forEach { (key, value) ->
            edit.putString(key, value)
        }
        edit.apply()

    }
}