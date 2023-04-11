package com.incava.gangchuplace.adapter

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.view.main.GangChuFragment
import com.incava.gangchuplace.view.search.SearchActivity
import com.incava.gangchuplace.viewmodel.GangChuViewModel

object GangChuBindingAdapter{
    @JvmStatic
    @BindingAdapter("setGangChuAdapter")
    fun setGangChuAdapter (view : RecyclerView, item : MutableList<GangChuPreview>){
        view.apply {
            adapter = GangChuAdapter(item)
            layoutManager = GridLayoutManager(view.context,2,GridLayoutManager.VERTICAL,false)
        }
    }
    @JvmStatic
    @BindingAdapter("setGangChuToolbar")
    fun setGangChuToolbar (view : Toolbar, vm : GangChuViewModel){
        view.setOnMenuItemClickListener { vm.itemClick(view,it)
            true }
    }

}