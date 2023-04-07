package com.incava.gangchuplace.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.incava.gangchuplace.R
import de.hdodenhof.circleimageview.CircleImageView

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view : CircleImageView, url : String){
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.vector_thumb_up)
            .into(view)
    }
    @JvmStatic
    @BindingAdapter("setImages")
    fun setImages(view : ImageView, url : String){
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.normal_image)
            .into(view)
    }


}