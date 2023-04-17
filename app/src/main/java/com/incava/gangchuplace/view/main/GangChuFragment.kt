package com.incava.gangchuplace.view.main

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu){

    override fun init() {
        binding.gangChu = this
        binding.gangChuVM = ViewModelProvider(requireActivity())[GangChuViewModel::class.java]
    }
}