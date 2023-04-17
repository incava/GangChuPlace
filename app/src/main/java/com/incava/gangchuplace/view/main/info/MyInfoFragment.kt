package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyInfoBinding
import com.incava.gangchuplace.viewmodel.MyInfoViewModel

class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {

    private val myInfoViewModel : MyInfoViewModel by viewModels()
    override fun init() {
        binding.apply {
            myInfo = this@MyInfoFragment
            binding.myInfoVM = myInfoViewModel
        }
    }

    //리뷰Fragment이동
    fun moveMyReview(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_myReviewFragment)
    }
    //찜한 가게가 있는 Fragment로 이동
    fun moveMyHeartStore(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_myHeartFragment)
    }

    //정보변경fragment로 이동
    fun moveReviseInfo(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_reviseInfoFragment)
    }

    //비밀번호 확인으로 이동.
    fun moveRevisePassword(){
        requireActivity().findNavController(R.id.main_nav_host).navigate(R.id.action_baseContainerFragment_to_revisePasswordFragment)
    }

}