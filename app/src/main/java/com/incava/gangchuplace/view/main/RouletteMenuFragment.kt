package com.incava.gangchuplace.view.main

import android.graphics.Color
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.bluehomestudio.luckywheel.WheelItem
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.RouletteAdapter
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRouletteMenuBinding
import com.incava.gangchuplace.viewmodel.RouletteViewModel


class RouletteMenuFragment : BaseFragment<FragmentRouletteMenuBinding>(R.layout.fragment_roulette_menu) {
    private val vm : RouletteViewModel by viewModels()
    override fun init() {
        binding.rouletteMenu = this
        binding.rouletteVM = vm
//        var a = RouletteAdapter() //뷰바인딩으로 에러가 어디서 발생하는지 확인.
//        Log.i("vmItem",vm.rouletteList.value.toString())
//        a.rouletteArray = vm.rouletteList.value ?: mutableListOf()
//        binding.recyclerView.adapter = a
    }

    fun goRoulette(){
        findNavController().navigate(R.id.action_rouletteMenuFragment_to_rouletteFragment)
    }

}