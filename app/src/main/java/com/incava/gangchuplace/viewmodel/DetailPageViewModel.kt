package com.incava.gangchuplace.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.main.info.MyReviewFragmentDirections
import com.incava.gangchuplace.view.write.DetailPageFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.ReviewStoreRepo

/**
 * 가게 정보를 가지고 있는 fragment
 */
class DetailPageViewModel : ViewModel() {

    private val reviewStoreRepo by lazy { ReviewStoreRepo() }

    private var _detailPageList = reviewStoreRepo.storeReviewList
    val detailPageList: MutableLiveData<MutableList<MyReviewInfo>> get() = _detailPageList

    var gangChuPreview = MutableLiveData<GangChuPreview>()

    init {
        gangChuPreview.value = GangChuPreview()
        _detailPageList.value = mutableListOf()
    }


    fun setGangChuPreview(gangChuPreview: GangChuPreview) {
        this.gangChuPreview.postValue(gangChuPreview)
        //리뷰 배열을 요청 하는 메서드
        reviewStoreRepo.loadStoreReviewList(gangChuPreview.storePlace.title)
    }


    // ReviewPage로 가는 이벤트 핸들러.
    fun moveReviewPage(view: View, myReviewInfo: MyReviewInfo) {
        val item = Gson().toJson(myReviewInfo)
        var action : NavDirections =
           DetailPageFragmentDirections.actionDetailPageFragmentToReviewPageFragment(myReviewInfo = item) //그에 맞는 액션
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }


}