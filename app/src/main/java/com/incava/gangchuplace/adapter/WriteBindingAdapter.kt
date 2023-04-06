package com.incava.gangchuplace.adapter

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.viewmodel.WriteViewModel

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

    @JvmStatic
    @BindingAdapter("setToolbar")
    fun setToolbar(view: Toolbar, writeViewModel: WriteViewModel) {
        view.setupWithNavController(view.findNavController())
        view.title = "장소 평가"
        view.setOnMenuItemClickListener {
            writeViewModel.finishReview(view)
            true
        }
    }

}