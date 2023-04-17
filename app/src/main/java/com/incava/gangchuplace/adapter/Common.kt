package com.incava.gangchuplace.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.User
import de.hdodenhof.circleimageview.CircleImageView

object Common {
    @JvmStatic
    @BindingAdapter("recyclerAdapter")
    //리사이클러뷰와 ViewModel을 이어줄 바인딩 어댑터
    fun recyclerAdapter(view: RecyclerView, items: Any) {
        view.apply {
            if (items is MutableList<*>) {
                if (items.all { it is RouletteMenuModel }) {
                    adapter = RouletteAdapter(items as MutableList<RouletteMenuModel>)
                    layoutManager = LinearLayoutManager(view.context)
                } else if (items.all { it is GangChuPreview }) {
                    adapter = GangChuAdapter(items as MutableList<GangChuPreview>)
                    layoutManager = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
                } else if (items.all { it is StorePlace }) {
                    adapter = WriteAdapter(items as MutableList<StorePlace>)
                    layoutManager = LinearLayoutManager(view.context)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: CircleImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.normal_image)
            .error(R.drawable.vector_thumb_up)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.normal_image)
            .into(view)
    }




}