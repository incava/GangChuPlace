package com.incava.gangchuplace.adapter

import android.R.attr.text
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.adapter.Common.getSharedPreference


object GangChuBindingAdapter {


    @JvmStatic
    @BindingAdapter("setNameBind")
    fun setNameBind(view : TextView, noUse: String){
        view.text = getSharedPreference(view.context).nickname
    }

    @JvmStatic
    @BindingAdapter("setFilterName")
    fun setFilterName(view : TextView, name:String){
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, 0)
        view.text = content
    }


}