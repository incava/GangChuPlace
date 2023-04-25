package com.incava.gangchuplace.viewmodel.repository

import android.text.Html
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.StorePlaceDTO
import com.incava.gangchuplace.network.NaverPlaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class StoreSearchRepo {

    var searchData = MutableLiveData<MutableList<StorePlace>>()

    fun searchPlace(query: String, retrofit: Retrofit) {
        Log.i("여기까지 됨", "ㅇㅇ")
        retrofit.create(NaverPlaceService::class.java)
            .searchDataByString(query)
            .enqueue(object :Callback<StorePlaceDTO>{
                override fun onResponse(
                    call: Call<StorePlaceDTO>,
                    response: Response<StorePlaceDTO>
                ) {
                    Log.i("장소검색", response.body()?.items.toString())
                    response.body()?.items?.forEach {
                        it.title = Html.fromHtml(it.title,Html.FROM_HTML_MODE_LEGACY).toString()
                    }
                    response.body()?.items ?: return //이 부분 !?
                    searchData.postValue(response.body()?.items)
                }

                override fun onFailure(call: Call<StorePlaceDTO>, t: Throwable) {
                    Log.i("error", t.message.toString())
                }

            })
    }


}