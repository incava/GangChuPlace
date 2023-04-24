package com.incava.gangchuplace.view.main

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRouletteBinding
import com.incava.gangchuplace.viewmodel.RouletteViewModel

class RouletteFragment : BaseFragment<FragmentRouletteBinding>(R.layout.fragment_roulette) {
    private val rouletteViewModel : RouletteViewModel by activityViewModels()
    override fun init() {
        rouletteViewModel.point.value = -1
        binding.rouletteVM = rouletteViewModel
        binding.toolbar.setupWithNavController(findNavController())
    }








}