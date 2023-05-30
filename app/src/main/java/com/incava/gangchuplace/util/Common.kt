package com.incava.gangchuplace.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.incava.gangchuplace.model.User
import java.text.SimpleDateFormat
import java.util.Locale

object Common {
    // 계속 사용 할 것이기 때문에 static으로 생성.
    val fireStore = FirebaseFirestore.getInstance()

    //Dialog 띄울 때 다른 이벤트가 없을 경우 사용 할 common method
    fun showDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA)
        val currentDateTime = System.currentTimeMillis()
        return sdf.format(currentDateTime)
    }

    // sharedPreference 파일 가져올 수 있는 메서드
    fun getSharedPreference(context: Context): User {
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

}