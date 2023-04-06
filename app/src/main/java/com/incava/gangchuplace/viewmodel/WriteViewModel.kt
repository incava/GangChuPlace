package com.incava.gangchuplace.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.write.WriteActivity

class WriteViewModel : ViewModel() {


    private var _placeList = MutableLiveData<MutableList<StorePlace>>()
    val placeList: MutableLiveData<MutableList<StorePlace>> get() = _placeList

    private lateinit var storePlace: StorePlace

    init {
        val a = mutableListOf<StorePlace>()
        repeat(6) {
            a.add(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"))
        }
        _placeList.value = a
    }

    //버튼을 눌렀을때,
    fun moveNext(view: View, store: StorePlace) {
        storePlace = store
        Toast.makeText(view.context, storePlace.toString(), Toast.LENGTH_SHORT).show()
        view.findNavController().navigate(R.id.action_writeSearchFragment_to_writeReviewFragment)
    }

    fun finishReview(view: View) {
        // 저장하는 기능 수행.

        //다 수행하고 나서 view의 액티비티를 finish()
        (view.context as WriteActivity).finish()
    }


}