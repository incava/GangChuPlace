package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.write.DetailPageFragmentDirections

/**
 * 가게 정보를 가지고 있는 fragment
 */
class DetailPageViewModel(val storePlace: StorePlace) : ViewModel() {

    private var _detailPageList = MutableLiveData<MutableList<ReviewInfo>>()
    val detailPageList: MutableLiveData<MutableList<ReviewInfo>> get() = _detailPageList

    init {
        Log.i("storePlace", storePlace.toString())
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
                , storePlace.title)
            )
        }
        _detailPageList.value = list
    }
    fun moveReviewPage(view : View, reviewInfo: ReviewInfo){
        val item = Gson().toJson(reviewInfo)
        var action = DetailPageFragmentDirections.actionDetailPageFragmentToReviewPageFragment(item)
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }


}