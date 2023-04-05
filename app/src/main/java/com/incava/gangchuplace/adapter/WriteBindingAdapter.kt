package com.incava.gangchuplace.adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.model.StorePlace

object WriteBindingAdapter {

    @JvmStatic
    @BindingAdapter("setWriteAdapter")
    fun setWriteAdapter(view: RecyclerView, item: MutableList<StorePlace>) {
        view.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = WriteAdapter(item)
            //Log.i("write",item.toString())
        }
    }
}