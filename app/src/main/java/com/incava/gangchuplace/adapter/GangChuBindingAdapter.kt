package com.incava.gangchuplace.adapter

import android.R.attr.text
import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import kotlin.math.roundToLong


object GangChuBindingAdapter {


    @JvmStatic
    @BindingAdapter("setNameBind")
    fun setNameBind(view: TextView, noUse: String) {
        view.text = getSharedPreference(view.context).nickname
    }

    @JvmStatic
    @BindingAdapter("setFilterName")
    fun setFilterName(view: TextView, name: String) {
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, 0)
        view.text = content
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("translateDistance")
    fun translateDistance(view: TextView, distance: Int) {
        view.text = "지금 거리에서 ${when (distance) {
            in 0 until 1000 -> "$distance m"
            else -> "${(((distance / 100.0).toInt())/10.0)} km"
        }}"
    }


}