package com.incava.gangchuplace.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.viewmodel.DetailPageViewModel

object DetailPageBindingAdapter {

    @JvmStatic
    @BindingAdapter("setDetailAdapter")
    fun setDetailAdapter (view : RecyclerView, vm : DetailPageViewModel) {
        view.apply {
            adapter = DetailAdapter(vm)
            layoutManager = LinearLayoutManager(view.context)
        }

    }
}