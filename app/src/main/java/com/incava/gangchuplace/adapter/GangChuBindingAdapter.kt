package com.incava.gangchuplace.adapter

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel

object GangChuBindingAdapter {


    @JvmStatic
    @BindingAdapter("setNameBind")
    fun setNameBind(view : TextView,txt : String){
        view.text = getSharedPreference(view).nickname
    }

    @JvmStatic
    @BindingAdapter("setFilterName")
    fun ssetFilterName(view : TextView, gangChuVM : GangChuViewModel){
        var mSpannableString = SpannableString(gangChuVM.filterName)
        mSpannableString.setSpan(UnderlineSpan(), 0,mSpannableString.length, 0 )
        view.text = mSpannableString
    }


}