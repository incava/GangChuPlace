package com.incava.gangchuplace.viewmodel.repository

import android.text.Html
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.StorePlaceDTO
import com.incava.gangchuplace.network.NaverPlaceService
import com.incava.gangchuplace.network.RetrofitHelper
import com.incava.gangchuplace.util.Common.transformLatLngFromKatech
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

/**
 * 네이버에서 가게 정보에 관련된 값을 가져 오는 Repo
 */
class WriteStoreSearchRepo {

    var searchData = MutableLiveData<MutableList<StorePlace>>()
    var call: Call<StorePlaceDTO>? = null

    fun searchNaverPlace(query: String) {
        //retrofit 작업중이였던 것은 캔슬하고 새로 작업
        if (call?.isExecuted == true) {
            call?.cancel()
            Log.i("지나갑니다.", "지나가요")
        }
        call = RetrofitHelper.retrofitHelper(baseUrl = "https://openapi.naver.com/")
            .create(NaverPlaceService::class.java)
            .searchDataByString(query)
        call?.enqueue(object : Callback<StorePlaceDTO> {
            override fun onResponse(
                call: Call<StorePlaceDTO>,
                response: Response<StorePlaceDTO>
            ) {
                Log.i("장소검색", response.body()?.items.toString())
                response.body()?.items?.forEach {
                    it.title = Html.fromHtml(it.title, Html.FROM_HTML_MODE_COMPACT).toString()
//                    val latLng = Tm128(it.mapx, it.mapy).toLatLng()
                    val latLng = LatLng(transformLatLngFromKatech(it.mapx), transformLatLngFromKatech(it.mapy))
                    it.mapx = latLng.longitude
                    it.mapy = latLng.latitude
                    Log.i("mapXX",it.mapx.toString())
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