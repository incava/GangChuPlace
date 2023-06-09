package com.incava.gangchuplace.view.search

import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R

import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentStoreSearchResultBinding
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel


class StoreSearchResultFragment :
    BaseFragment<FragmentStoreSearchResultBinding>(R.layout.fragment_store_search_result) {
    private val gangChuViewModel: GangChuViewModel by activityViewModels()
    val uniqueId by lazy{
        getSharedPreference(requireContext()).run {
            "${loginRoute}+${email}"
        }
    }
    override fun init() {
        binding.gangChuVM = gangChuViewModel
        gangChuViewModel.requestGangChuFilterSearchList(uniqueId)
    }
}