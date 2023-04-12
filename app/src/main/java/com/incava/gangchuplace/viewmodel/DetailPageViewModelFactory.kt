package com.incava.gangchuplace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.model.StorePlace

class DetailPageViewModelFactory (private val storePlace: StorePlace) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailPageViewModel::class.java)){
            return DetailPageViewModel(storePlace) as T
        }
        throw IllegalArgumentException("unKnown ViewModel class")
    }

}