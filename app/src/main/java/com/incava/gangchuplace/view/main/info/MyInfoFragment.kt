package com.incava.gangchuplace.view.main.info

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyInfoBinding

class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {
    override fun init() {
        binding.myInfo = this
    }

    fun moveMyReview(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_myReviewFragment)
    }
    fun moveMyHeartStore(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_myHeartFragment)
    }

}