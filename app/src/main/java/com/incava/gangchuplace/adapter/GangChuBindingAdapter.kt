package com.incava.gangchuplace.adapter

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.viewmodel.GangChuViewModel

object GangChuBindingAdapter{
    @JvmStatic
    @BindingAdapter("setGangChuToolbar")
    fun setGangChuToolbar (view : Toolbar, vm : GangChuViewModel){
        view.setOnMenuItemClickListener { vm.itemClick(view,it)
            true }
    }

}