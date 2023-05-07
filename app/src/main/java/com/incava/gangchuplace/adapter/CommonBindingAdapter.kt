package com.incava.gangchuplace.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.incava.gangchuplace.R
import de.hdodenhof.circleimageview.CircleImageView

object CommonBindingAdapter {


    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, url: String?) {
        Log.i("setImage", url.toString())
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


}