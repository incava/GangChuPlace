package com.incava.gangchuplace.view.main.info

import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyInfoBinding

class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {
    override fun init() {
        binding.myInfo = this
    }

    fun moveMyReview(){
        findNavController().navigate(R.id.action_myInfoFragment_to_myReviewFragment)
    }
    fun moveMyHeartStore(){
        findNavController().navigate(R.id.action_myInfoFragment_to_myHeartFragment)
    }

}