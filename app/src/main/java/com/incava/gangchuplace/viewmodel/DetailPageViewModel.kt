package com.incava.gangchuplace.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.StorePlace

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
                )
            )
        }
        _detailPageList.value = list
    }


}