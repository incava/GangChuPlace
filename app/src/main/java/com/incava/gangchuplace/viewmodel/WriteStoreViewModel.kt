package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.network.RetrofitHelper
import com.incava.gangchuplace.view.write.WriteSearchFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.StoreSearchRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class WriteStoreViewModel : ViewModel() {

    private val storeSearchRepo  by lazy {  StoreSearchRepo()}
    private val _placeList : MutableLiveData<MutableList<StorePlace>> get() = storeSearchRepo.searchData
    val placeList: MutableLiveData<MutableList<StorePlace>> get() = _placeList

    //가게 찾을 때 중복으로 실행을 막기위해 맴버변수로 선언.
    private var searchJob: Job? = null

    init {

//        val a = mutableListOf<StorePlace>()
//        repeat(6) {
//            a.add(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"))
//        }
//        _placeList.value = a
    }

    //버튼을 눌렀을때,
    fun moveNext(view: View, store: StorePlace) {
        var action = WriteSearchFragmentDirections.actionWriteSearchFragmentToWriteReviewFragment(storePlaceInfo = Gson().toJson(store))
        view.findNavController().navigate(action)
    }

    //검색시 textChange마다 호출.
    fun getStoreInfo(query : String){
        Log.i("query",query)
        //이전 2초동안 중복 작업이 있었다면 cancel
        val a = mutableListOf<StorePlace>()
        repeat(6) {
            a.add(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"))
        }
        _placeList.postValue(a)
        Log.i("playList",placeList.value.toString())

//        searchJob?.cancel()
//        searchJob = CoroutineScope(Dispatchers.IO).launch {
//            Log.i("코루틴","됨")
//            delay(2000L)
//            var retrofit = RetrofitHelper.retrofitHelper(baseUrl = "https://openapi.naver.com/")
//            //Retrofit으로 장소검색 하는 메서드.
//            storeSearchRepo.searchPlace(query, retrofit)
//        }
    }
}