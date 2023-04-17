package com.incava.gangchuplace.adapter

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.incava.gangchuplace.viewmodel.GangChuViewModel

object SearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("setSearchListener")
    fun setSearchListener(view: EditText, gangChuViewModel: GangChuViewModel) {
        view.requestFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT) // 키보드를 보여줍니다.
        view.setOnEditorActionListener {  _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 검색 작업 수행. 이동 및 filter 처리 호출.
                gangChuViewModel.moveSearchResult(view)
                true
            } else {
                false
            }
        }
    }


}