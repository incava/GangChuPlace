package com.incava.gangchuplace.viewmodel

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseContainerFragmentDirections
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.main.GangChuFragment
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.main.info.MyHeartFragment
import com.incava.gangchuplace.view.main.info.MyHeartFragmentDirections


class GangChuViewModel : ViewModel() {
    private var _gangChuList = MutableLiveData<MutableList<GangChuPreview>>()
    val gangChuList: MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuList

    init {
        val a = mutableListOf<GangChuPreview>()
        repeat(6) {
            a.add(
                GangChuPreview(
                    StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "900", "800"),
                    "인기 외 3명", 4.6, "", true,4.5
                )
            )
        }
        _gangChuList.value = a
    }

    fun setHeart(view: View, checked: Boolean, item: GangChuPreview) {
        val s = if (checked) "찜하기 완료" else "찜하기 해제"
        Toast.makeText(view.context, s, Toast.LENGTH_SHORT).show()
    }

    //이동시 글쓰기로 이동.
    fun moveWrite(view: View) {
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_nav_write_graph)
    }

    //View에 따른 뷰에서 DetailPageFragment로 이동.
    fun moveDetail(view: View,storePlace: StorePlace) {
        val item = Gson().toJson(storePlace)
        val action =
            if (view.findFragment<Fragment>() is GangChuFragment){ // GangChuFragment에서 이동
                BaseContainerFragmentDirections.actionBaseContainerFragmentToDetailPageFragment(storePlace = item)
            } else { // MyHeartFragment에서 이동 (view.findFragment<Fragment>() is MyHeartFragment)
                MyHeartFragmentDirections.actionMyHeartFragmentToDetailPageFragment(storePlace = item)
            }
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }

    fun itemClick(view: View, item: MenuItem) {
        when (item.itemId) {
            R.id.search -> { // searchFragment로 이동.
                (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_storeSearchFragment)
            }
            R.id.map -> view.findNavController()
                .navigate(R.id.action_gangChuFragment_to_mapFragment) // map으로 이동.
        }
        true
    }


}