package com.incava.gangchuplace.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.incava.gangchuplace.model.RouletteMenuModel

class RouletteViewModel : ViewModel() {

    private var _rouletteList = MutableLiveData<MutableList<RouletteMenuModel>>()
    val rouletteList : LiveData<MutableList<RouletteMenuModel>> get() = _rouletteList

    init {
        val a = mutableListOf<RouletteMenuModel>()
            repeat(6){
                a.add(RouletteMenuModel("짜장면"))
        }
        _rouletteList.value = a
        Log.i("vmInit1",a.toString())
        Log.i("vmInit2",rouletteList.value.toString())
        Log.i("vmInit3",_rouletteList.value.toString())
    }

    fun removeItem(item : RouletteMenuModel){
        _rouletteList.value?.remove(item)
        Log.i("delete",_rouletteList.value.toString())
    }
    fun addItem(item : String){
        var a : MutableList<RouletteMenuModel> = _rouletteList.value ?: mutableListOf()
        a.add(RouletteMenuModel(menuItem = item))
        _rouletteList.postValue(a)
        Log.i("addItem",rouletteList.value.toString())
    }



}