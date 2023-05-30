package com.incava.gangchuplace.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.main.info.MyReviewFragmentDirections
import com.incava.gangchuplace.view.write.DetailPageFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.MyReviewRepo

class MyReviewViewModel : ViewModel() {

    private val myReviewRepo by lazy { MyReviewRepo() }

    private var _myReviewList = myReviewRepo.reviewList
    val myReviewList: MutableLiveData<MutableList<MyReviewInfo>> get() = _myReviewList

    fun requestMyReview(id: String) {
        myReviewRepo.loadMyReview(id)
    }

    fun moveReviewPage(view: View, myReviewInfo: MyReviewInfo) {
        val item = Gson().toJson(myReviewInfo)
        var action: NavDirections =
            MyReviewFragmentDirections.actionMyReviewFragmentToReviewPageFragment(myReviewInfo = item)
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }
}