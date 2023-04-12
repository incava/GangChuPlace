package com.incava.gangchuplace.view.write

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentDetailPageBinding
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.viewmodel.DetailPageViewModel
import com.incava.gangchuplace.viewmodel.DetailPageViewModelFactory

class DetailPageFragment : BaseFragment<FragmentDetailPageBinding>(R.layout.fragment_detail_page) {
    val args: DetailPageFragmentArgs by navArgs()
   val storePlace: StorePlace by lazy {
       Gson().fromJson(args.storePlace, StorePlace::class.java)
   }
       // ?: StorePlace("", "", "", "", "", "")
    private val detailPageViewModel: DetailPageViewModel by viewModels {
        DetailPageViewModelFactory(storePlace)
    }

    override fun init() {
        Log.i("item", storePlace.toString())
       binding.detailPageVM = detailPageViewModel
    }

}