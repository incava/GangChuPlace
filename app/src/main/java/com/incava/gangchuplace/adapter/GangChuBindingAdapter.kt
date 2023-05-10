package com.incava.gangchuplace.adapter

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel


object GangChuBindingAdapter {


    @JvmStatic
    @BindingAdapter("setNameBind")
    fun setNameBind(view: TextView, noUse: String) {
        view.text = getSharedPreference(view.context).nickname
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

    fun setAdapters(view : Spinner, gangChuVM : GangChuViewModel){
        view.apply {
            adapter = ArrayAdapter(
                view.context, R.layout.simple_dropdown_item_1line,
                resources.getStringArray(com.incava.gangchuplace.R.array.gang_chu_filter)
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    if (parent != null) {
                        gangChuVM.setSortFilterList(parent.getItemAtPosition(position).toString())
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

}
