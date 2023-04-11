package com.incava.gangchuplace.viewmodel

import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.search.SearchActivity
import com.incava.gangchuplace.view.write.WriteActivity

class GangChuViewModel : ViewModel() {
    private var _gangChuList = MutableLiveData<MutableList<GangChuPreview>>()
    val gangChuList : MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuList

    init {
        val a = mutableListOf<GangChuPreview>()
        repeat(6) {
            a.add(GangChuPreview(StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "900", "800"),
                "인기 외 3명",4.6,"",true))
        }
        _gangChuList.value = a
    }

    fun setHeart(view : View, checked : Boolean, item: GangChuPreview){
        val s = if (checked) "찜하기 완료" else "찜하기 해제"
        Toast.makeText(view.context, s, Toast.LENGTH_SHORT).show()
    }
    //이동시 글쓰기로 이동.
    fun moveWrite(view : View){
        view.context.startActivity(Intent(view.context, WriteActivity::class.java))
    }
    fun itemClick(view : View, item : MenuItem){
        when (item.itemId) {
            R.id.search -> view.context.startActivity(Intent(view.context, SearchActivity::class.java)) // 검색창으로 이동
            R.id.map -> view.findNavController().navigate(R.id.action_gangChuFragment_to_mapFragment) // map으로 이동.
        }
        true
    }
}