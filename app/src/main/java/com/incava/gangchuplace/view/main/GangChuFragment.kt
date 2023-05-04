package com.incava.gangchuplace.view.main

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common.getSharedPreference
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu){

    val gangChuViewModel by lazy {  ViewModelProvider(requireActivity())[GangChuViewModel::class.java]}
    override fun init() {
        binding.gangChu = this
        binding.gangChuVM = gangChuViewModel
        val uniqueId = getSharedPreference(requireContext()).run {
            "${loginRoute}+${id}"
        }
        gangChuViewModel.loadGangChuList(uniqueId)
    }
}