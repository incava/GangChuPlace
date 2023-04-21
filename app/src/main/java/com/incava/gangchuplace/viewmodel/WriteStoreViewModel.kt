package com.incava.gangchuplace.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.write.WriteSearchFragmentDirections

class WriteStoreViewModel : ViewModel() {

    private var _placeList = MutableLiveData<MutableList<StorePlace>>()
    val placeList: MutableLiveData<MutableList<StorePlace>> get() = _placeList
    init {
        val a = mutableListOf<StorePlace>()
        repeat(6) {
            a.add(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"))
        }
        _placeList.value = a
    }

    //버튼을 눌렀을때,
    fun moveNext(view: View, store: StorePlace) {
        var action = WriteSearchFragmentDirections.actionWriteSearchFragmentToWriteReviewFragment(storePlaceInfo = Gson().toJson(store))
        view.findNavController().navigate(action)
    }
}