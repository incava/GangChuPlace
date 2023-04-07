package com.incava.gangchuplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace

class GangChuViewModel : ViewModel() {
    private var _gangChuList = MutableLiveData<MutableList<GangChuPreview>>()
    val gangChuList : MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuList

    init {
        val a = mutableListOf<GangChuPreview>()
        repeat(6) {
            a.add(GangChuPreview(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "mapx", "mapy"),
                "인기 외 3명",4.6))
        }
        _gangChuList.value = a
    }
}