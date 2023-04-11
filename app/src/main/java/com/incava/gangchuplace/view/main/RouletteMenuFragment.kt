package com.incava.gangchuplace.view.main

import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRouletteMenuBinding
import com.incava.gangchuplace.viewmodel.RouletteViewModel


class RouletteMenuFragment :
    BaseFragment<FragmentRouletteMenuBinding>(R.layout.fragment_roulette_menu) {
    private val vm: RouletteViewModel by activityViewModels()
    override fun init() {
        binding.rouletteVM = vm
    }

}