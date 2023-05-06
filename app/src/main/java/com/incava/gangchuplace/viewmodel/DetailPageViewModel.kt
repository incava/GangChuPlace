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

/**
 * 가게 정보를 가지고 있는 fragment
 */
class DetailPageViewModel : ViewModel() {

    private var _detailPageList = MutableLiveData<MutableList<ReviewInfo>>()
    val detailPageList: MutableLiveData<MutableList<ReviewInfo>> get() = _detailPageList

     var gangChuPreview = MutableLiveData<GangChuPreview>()

    init {
        gangChuPreview.postValue(GangChuPreview())
    }


    fun setGangChuPreview(gangChuPreview : GangChuPreview){
        this.gangChuPreview.postValue(gangChuPreview)
        var list = mutableListOf<ReviewInfo>()
        repeat(6) {
            list.add(
                ReviewInfo(
                    "인기",
                    "",
                    "2023-04-12",
                    "4.5",
                    "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠"
                    , gangChuPreview.storePlace.title)
            )
        }
        _detailPageList.value = list
        Log.i("storePlace", gangChuPreview.storePlace.toString())
    }
    fun setUser(user : User){
        var list = mutableListOf<ReviewInfo>()
        repeat(6) {
            list.add(
                ReviewInfo(
                    "인기",
                    "",
                    "2023-04-12",
                    "4.5",
                    "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠" +
                            "아니 너무 맛있잖아요. 나도 가고 싶어서 간건데 같이갈사람 선착순 1명 구해요 :D 대박적!!! 그쵸 대박이죠"
                    , user.nickname)
            )
        }
        _detailPageList.value = list
        Log.i("storePlace", user.toString())
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