package com.incava.gangchuplace.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluehomestudio.luckywheel.WheelItem
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.model.RouletteModel

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

    fun removeItem(item : RouletteModel){
        _rouletteList.value
    }
    fun addItem(item : String){
        var a : MutableList<RouletteMenuModel> = _rouletteList.value ?: mutableListOf()
        a.add(RouletteMenuModel(menuItem = item))
        _rouletteList.postValue(a)
        Log.i("addItem",rouletteList.value.toString())
    }



}