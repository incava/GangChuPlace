package com.incava.gangchuplace.adapter

import android.Manifest
import android.R.attr.text
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
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
        // 위치 권한에 따라 view가 보이도록 or 보이지 않도록 하기.
        if (view.context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            view.visibility = View.INVISIBLE else view.visibility = View.VISIBLE

        view.text = "지금 거리에서 ${
            when (distance) {
                in 0 until 1000 -> "$distance m"
                else -> "${(((distance / 100.0).toInt()) / 10.0)} km"
            }
        }"
    }
}
