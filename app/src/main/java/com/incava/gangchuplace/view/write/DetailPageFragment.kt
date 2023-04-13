package com.incava.gangchuplace.view.write

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentDetailPageBinding
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.viewmodel.DetailPageViewModel
import com.incava.gangchuplace.viewmodel.DetailPageViewModelFactory

class DetailPageFragment : BaseFragment<FragmentDetailPageBinding>(R.layout.fragment_detail_page) {

    // 받아온 args를 가져와 storePlace에 Json화.
    val args: DetailPageFragmentArgs by navArgs()
    val storePlace: StorePlace by lazy {
        Gson().fromJson(args.storePlace, StorePlace::class.java)
    }

    // 뷰모델에 생성자가 필요해 factory를 이용한 뷰모델 객체 생성.
    private val detailPageViewModel: DetailPageViewModel by viewModels {
        DetailPageViewModelFactory(storePlace)
    }

    override fun init() {
        binding.apply {
            detailPageVM = detailPageViewModel
            // 툴바를 연결
            toolbar.setupWithNavController(findNavController())
        }
    }

}