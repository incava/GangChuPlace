package com.incava.gangchuplace.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.application.GlobalApplication
import com.incava.gangchuplace.viewmodel.ReviseInfoViewModel

object ReviseBindingAdapter {

    @JvmStatic
    @BindingAdapter("bindingTextNickname")
    fun bindingTextNickname(view: TextView,reviseInfoViewModel: ReviseInfoViewModel) {
        view.text = reviseInfoViewModel.nickname
    }
}