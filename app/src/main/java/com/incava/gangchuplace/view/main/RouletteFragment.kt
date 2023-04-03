package com.incava.gangchuplace.view.main

import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRouletteBinding
import com.incava.gangchuplace.viewmodel.RouletteViewModel

class RouletteFragment : BaseFragment<FragmentRouletteBinding>(R.layout.fragment_roulette) {
    private val vm : RouletteViewModel by activityViewModels()
    override fun init() {
        binding.vm = vm
    }








}