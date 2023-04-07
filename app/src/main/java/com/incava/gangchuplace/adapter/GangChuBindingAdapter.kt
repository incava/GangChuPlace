package com.incava.gangchuplace.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.model.GangChuPreview

object GangChuBindingAdapter{
    @JvmStatic
    @BindingAdapter("setGangChuAdapter")
    fun setGangChuAdapter (view : RecyclerView, item : MutableList<GangChuPreview>){
        view.apply {
            adapter =
        }
    }
}