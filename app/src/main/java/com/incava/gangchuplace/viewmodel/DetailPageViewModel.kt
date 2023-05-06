package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.main.info.MyReviewFragment
import com.incava.gangchuplace.view.main.info.MyReviewFragmentDirections
import com.incava.gangchuplace.view.write.DetailPageFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.ReviewStoreRepo

/**
 * 가게 정보를 가지고 있는 fragment
 */
class DetailPageViewModel : ViewModel() {

    private val reviewStoreRepo by lazy { ReviewStoreRepo() }

    private var _detailPageList = reviewStoreRepo.storeReviewList
    val detailPageList: MutableLiveData<MutableList<ReviewInfo>> get() = _detailPageList

     var gangChuPreview = MutableLiveData<GangChuPreview>()

    init {
        gangChuPreview.value = GangChuPreview()
        _detailPageList.value = mutableListOf()
    }


    fun setGangChuPreview(gangChuPreview : GangChuPreview){
        this.gangChuPreview.postValue(gangChuPreview)
        //리뷰 배열을 요청 하는 메서드
        reviewStoreRepo.loadStoreReviewList(gangChuPreview.storePlace.title)
    }
    fun setUser(user : User){
        //todo 내 리뷰 구현부
    }



    // ReviewPage로 가는 이벤트 핸들러.
    fun moveReviewPage(view : View, reviewInfo: ReviewInfo){
        val item = Gson().toJson(reviewInfo)
        var action : NavDirections =
            if (view.findFragment<Fragment>() is MyReviewFragment) { // 만약 MyReview Fragment에서 온 뷰라면?
                MyReviewFragmentDirections.actionMyReviewFragmentToReviewPageFragment(item) //그에 맞는 액션
            } else{ // DetailPageFragment에서 ReviewPage로 가는 액션.
                DetailPageFragmentDirections.actionDetailPageFragmentToReviewPageFragment(item)
            }
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }


}