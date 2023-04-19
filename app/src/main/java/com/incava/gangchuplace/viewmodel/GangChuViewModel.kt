package com.incava.gangchuplace.viewmodel

import android.util.Log
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
import com.incava.gangchuplace.view.main.info.MyHeartFragmentDirections
import com.incava.gangchuplace.view.search.StoreSearchResultFragment
import com.incava.gangchuplace.view.search.StoreSearchResultFragmentDirections


class GangChuViewModel : ViewModel() {
    private var _gangChuList = MutableLiveData<MutableList<GangChuPreview>>()
    val gangChuList: MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuList

    private var _gangChuSearchList = MutableLiveData<MutableList<GangChuPreview>>()
    val gangChuSearchList: MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuSearchList

    var researchKeyword = ""

    init {

        val a = mutableListOf<GangChuPreview>()
        val b = mutableListOf<GangChuPreview>()
        repeat(6) {
            a.add(
                GangChuPreview(
                    StorePlace("갈비집", "육류", "맛있는 갈비집", "address", "900", "800"),
                    "인기 외 3명", 4.6, "", true, 4.5
                )
            )
            b.add(
                GangChuPreview(
                    StorePlace("방탈출", "비트", "비트포비아", "address123", "90011", "80044"),
                    "상완 외 2명", 4.66, "", false, 4.8
                )
            )
        }
        _gangChuList.value = a
        _gangChuSearchList.value = b
    }

    fun setHeart(view: View, checked: Boolean, item: GangChuPreview) {
        val s = if (checked) "찜하기 완료" else "찜하기 해제"
        Toast.makeText(view.context, s, Toast.LENGTH_SHORT).show()
    }

    //이동시 글쓰기로 이동.


    fun setSearchKeyword(text : String){
        Log.i("keyword",text)
        researchKeyword = text
    }

    //GangChuFragment에서 호출할 이벤트 핸들러
    fun itemClick(view: View, item: MenuItem) {
        when (item.itemId) {
            R.id.search -> { // searchFragment로 이동.
                (view.context as MainActivity).findNavController(R.id.main_nav_host)
                    .navigate(R.id.action_baseContainerFragment_to_storeSearchFragment)
            }

            R.id.map -> view.findNavController()
                .navigate(R.id.action_gangChuFragment_to_mapFragment) // map으로 이동.
        }
    }
    //    action_storeSearchResultFragment_to_detailPageFragment

    //storeSearchFragment에서 검색결과를 보기위한 이벤트 핸들러
    fun moveSearchResult(view:View){
        (view.context as MainActivity).findNavController(R.id.main_nav_host)
            .navigate(R.id.action_storeSearchFragment_to_storeSearchResultFragment)
    }

    // GangChuFragment에서 writeFragment로 이동하기 위한 이벤트 핸들러
    fun moveWrite(view: View) {
        (view.context as MainActivity).findNavController(R.id.main_nav_host)
            .navigate(R.id.action_baseContainerFragment_to_nav_write_graph)
    }


    //View에 따른 뷰에서 DetailPageFragment로 이동.
    fun moveDetail(view: View, storePlace: StorePlace) {
        val item = Gson().toJson(storePlace)
        val action =
            if (view.findFragment<Fragment>() is GangChuFragment) { // GangChuFragment에서 이동
                BaseContainerFragmentDirections.actionBaseContainerFragmentToDetailPageFragment(
                    storePlace = item
                )
            }else if(view.findFragment<Fragment>() is StoreSearchResultFragment){ // StoreSearchResultFragment에서 이동.
                StoreSearchResultFragmentDirections.actionStoreSearchResultFragmentToDetailPageFragment(storePlace = item)

            } else{ // MyHeartFragment에서 이동 (view.findFragment<Fragment>() is MyHeartFragment)
                MyHeartFragmentDirections.actionMyHeartFragmentToDetailPageFragment(storePlace = item)
            }
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }


}