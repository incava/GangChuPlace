package com.incava.gangchuplace.adapter

import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.model.ReviewInfo


/**
 * 툴바에 이벤트가 필요해 바인딩으로 속성추가.
 */
object ReviewPageBindingAdapter {


    //Todo 툴바 링크 전송 관련.
    @JvmStatic
    @BindingAdapter("setReviewPageToolbar")
    fun setReviewPageToolbar(view : Toolbar,reviewInfo: ReviewInfo){
        view.apply {
            setupWithNavController(findNavController())
            setOnMenuItemClickListener {
                Toast.makeText(context, "공유 클릭!", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}