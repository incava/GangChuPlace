package com.incava.gangchuplace.view.main

import android.content.Intent
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.view.search.SearchActivity
import com.incava.gangchuplace.view.write.WriteActivity

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu){

    override fun init() {
        binding.gangChu = this
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> startActivity(Intent(requireActivity(),SearchActivity::class.java)) // 검색창으로 이동
                R.id.map -> findNavController().navigate(R.id.action_gangChuFragment_to_mapFragment) // map으로 이동.
                else -> Log.i("Menuitem", "error")
            }
            true
        }

    }

    fun moveWrite(){
        Log.i("moveWrite", "good")
        startActivity(Intent(requireActivity(),WriteActivity::class.java))
    }


}