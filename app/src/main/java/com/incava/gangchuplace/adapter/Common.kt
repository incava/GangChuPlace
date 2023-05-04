package com.incava.gangchuplace.adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.WriteViewModel
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object Common {
    // 계속 사용 할 것이기 때문에 static으로 생성.
    val fireStore = FirebaseFirestore.getInstance()

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA)
        val  currentDateTime = System.currentTimeMillis()
        return sdf.format(currentDateTime)
    }


    //Dialog 띄울 때 다른 이벤트가 없을 경우 사용 할 common method
    fun showDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, url: String?) {
        Log.i("setImage",url.toString())
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.normal_image)
            .error(R.drawable.normal_image)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(view: CircleImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.normal_image)
            .into(view)
    }

    // sharedPreference 파일 가져올 수 있는 메서드
    fun getSharedPreference(context : Context): User {
        //파일이 없으면 빈 값으로 리턴.
        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            ?: return(User("","","","",""))

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