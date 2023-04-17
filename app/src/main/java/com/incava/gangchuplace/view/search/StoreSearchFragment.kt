package com.incava.gangchuplace.view.search

import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentStoreSearchBinding
import com.incava.gangchuplace.viewmodel.GangChuViewModel


class StoreSearchFragment :
    BaseFragment<FragmentStoreSearchBinding>(R.layout.fragment_store_search) {
    private val gangChuViewModel: GangChuViewModel by activityViewModels()
    override fun init() {
        binding.gangChuVM = gangChuViewModel
    }

}