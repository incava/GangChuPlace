package com.incava.gangchuplace.adapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.incava.gangchuplace.R
import de.hdodenhof.circleimageview.CircleImageView

object Common {
    // 계속 사용 할 것이기 때문에 static으로 생성.
    val fireStore = FirebaseFirestore.getInstance()

    //Dialog 띄울 때 다른 이벤트가 없을 경우 사용 할 common method
    fun showDialog(view: View, title: String, message: String) {
        AlertDialog.Builder(view.context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.normal_image)
            .error(R.drawable.normal_image)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(view: CircleImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.normal_image)
            .into(view)
    }


}