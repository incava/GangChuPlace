package com.incava.gangchuplace.network


import com.incava.gangchuplace.model.StorePlaceDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverPlaceService {
    @Headers("X-Naver-Client-Id:${CLIENTID}", "X-Naver-Client-Secret:${CLIENTSECRET}")
    @GET("v1/search/local.json?display=10")
    fun searchDataByString(@Query("query") query: String): Call<StorePlaceDTO>

}
const val CLIENTID = "SZyUgxhtaWJVUROz72wh"
const val CLIENTSECRET = "sr_wsxTKgO"
