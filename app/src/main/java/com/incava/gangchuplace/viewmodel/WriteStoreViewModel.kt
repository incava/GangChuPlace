package com.incava.gangchuplace.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.network.NaverPlaceService
import com.incava.gangchuplace.network.RetrofitHelper
import com.incava.gangchuplace.view.write.WriteSearchFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.StoreSearchRepo

class WriteStoreViewModel : ViewModel() {

    private val storeSearchRepo by lazy { StoreSearchRepo() }
    private val _placeList: MutableLiveData<MutableList<StorePlace>> get() = storeSearchRepo.searchData
    val placeList: MutableLiveData<MutableList<StorePlace>> get() = _placeList

    init {
        // 처음 배열이 만들어지지 않아 null이 발생하기에 초깃값을 준 후, get()으로 Livedata값 받기.
        _placeList.value = mutableListOf()
    }

    //버튼을 눌렀을 때,다음 화면으로
    fun moveNext(view: View, store: StorePlace) {
        var action = WriteSearchFragmentDirections.actionWriteSearchFragmentToWriteReviewFragment(
            storePlaceInfo = Gson().toJson(store)
        )
        view.findNavController().navigate(action)
    }

    //검색시 textChange마다 호출.
    fun getStoreInfo(query: String) {
        //Retrofit으로 장소검색 하는 메서드.
        storeSearchRepo.searchPlace(query)
    }
}