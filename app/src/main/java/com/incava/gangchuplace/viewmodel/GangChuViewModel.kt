package com.incava.gangchuplace.viewmodel

import android.app.Application
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseContainerFragmentDirections
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.view.main.GangChuFragment
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.main.info.MyHeartFragmentDirections
import com.incava.gangchuplace.view.search.StoreSearchResultFragment
import com.incava.gangchuplace.view.search.StoreSearchResultFragmentDirections
import com.incava.gangchuplace.viewmodel.repository.GangChuStoreRepo
import java.util.Random


class GangChuViewModel(application: Application) : AndroidViewModel(application) {

    val gangChuStoreRepo by lazy { GangChuStoreRepo(application) }

    private var _gangChuList = gangChuStoreRepo.storeList
    val gangChuList: MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuList

    private var _gangChuSearchList = gangChuStoreRepo.storeFilterList
    val gangChuSearchList: MutableLiveData<MutableList<GangChuPreview>> get() = _gangChuSearchList

    var researchKeyword = ""

    var filterName = MutableLiveData("평점순")

    var filterMethod = mutableListOf("평점순", "거리순", "리뷰순", "친구 리뷰순", "친구 평점순")


    init {
        _gangChuList.value = mutableListOf()
        _gangChuSearchList.value = mutableListOf()
    }

    fun setHeart(view: View, checked: Boolean, item: GangChuPreview) {
        val s = if (checked) "찜하기 완료" else "찜하기 해제"
        Toast.makeText(view.context, s, Toast.LENGTH_SHORT).show()
    }

    //이동시 글쓰기로 이동.

    fun setFilterList(view: View) {
        filterName.value = filterMethod[Random().nextInt(filterMethod.size)]
        Log.i("filterName", filterName.value.toString())
    }

    fun loadGangChuList(id: String) {
        gangChuStoreRepo.requestStoreList(id)
    }
    fun loadGangChuFilterSearchList(id:String){
        gangChuStoreRepo.requestFilterSearchStore(researchKeyword,id=id)
    }


    fun setSearchKeyword(text: String) {
        Log.i("keyword", text)
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
    fun moveSearchResult(view: View) {
        (view.context as MainActivity).findNavController(R.id.main_nav_host)
            .navigate(R.id.action_storeSearchFragment_to_storeSearchResultFragment)
    }

    // GangChuFragment에서 writeFragment로 이동하기 위한 이벤트 핸들러
    fun moveWrite(view: View) {
        (view.context as MainActivity).findNavController(R.id.main_nav_host)
            .navigate(R.id.action_baseContainerFragment_to_nav_write_graph)
    }


    //View에 따른 뷰에서 DetailPageFragment로 이동.
    fun moveDetail(view: View, gangChuPreview: GangChuPreview) {
        val item = Gson().toJson(gangChuPreview)
        val action =
            if (view.findFragment<Fragment>() is GangChuFragment) { // GangChuFragment에서 이동
                BaseContainerFragmentDirections.actionBaseContainerFragmentToDetailPageFragment(
                    gangChuPreview = item
                )
            } else if (view.findFragment<Fragment>() is StoreSearchResultFragment) { // StoreSearchResultFragment에서 이동.
                StoreSearchResultFragmentDirections.actionStoreSearchResultFragmentToDetailPageFragment(
                    gangChuPreview = item
                )

            } else { // MyHeartFragment에서 이동 (view.findFragment<Fragment>() is MyHeartFragment)
                MyHeartFragmentDirections.actionMyHeartFragmentToDetailPageFragment(gangChuPreview = item)
            }
        (view.context as MainActivity).findNavController(R.id.main_nav_host).navigate(action)
    }


}