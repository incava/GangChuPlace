package com.incava.gangchuplace.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.RouletteMenuModel

class RouletteViewModel : ViewModel() {

    //룰렛 아이템이 들어 가는 리스트
    private var _rouletteList = MutableLiveData<MutableList<RouletteMenuModel>>()
    val rouletteList : LiveData<MutableList<RouletteMenuModel>> get() = _rouletteList

    init {
        //setvalue -> 메인 쓰레드로 즉각적 반응, postValue -> 백그라운드에서 작동, 조금늦음.
        //값을 많이 넣지 않아 setvalue로 설정.
        val a = mutableListOf<RouletteMenuModel>()
            repeat(6){
                a.add(RouletteMenuModel("ex) 짜장면"))
        }
        _rouletteList.value = a
    }

    // 룰렛에 들어갈 아이템을 지우는 메서드
    fun removeItem(item: RouletteMenuModel) {
        val newList = _rouletteList.value?.toMutableList()
        newList?.remove(item)
        _rouletteList.value = newList?:return
        Log.i("delete", _rouletteList.value.toString())
    }
    // 룰렛에 들어갈 아이템을 얻는 메서드
    fun addItem(item : String){
        var newList = _rouletteList.value ?: mutableListOf()
        newList.add(RouletteMenuModel(menuItem = item))
        _rouletteList.value = newList
        Log.i("addItem",rouletteList.value.toString())
    }

    fun goRoulette(view : View){
        view.findNavController().navigate(R.id.action_rouletteMenuFragment_to_rouletteFragment)
    }



}