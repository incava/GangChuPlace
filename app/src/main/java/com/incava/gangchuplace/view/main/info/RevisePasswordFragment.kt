package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRevisePasswordBinding
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.util.TransformPasswordCode
import com.incava.gangchuplace.viewmodel.MyInfoViewModel
import com.incava.gangchuplace.viewmodel.RevisePasswordViewModel


class RevisePasswordFragment : BaseFragment<FragmentRevisePasswordBinding>(R.layout.fragment_revise_password) {
    private val revisePasswordViewModel : RevisePasswordViewModel by viewModels()
    override fun init() {
        binding.revisePasswordVM = revisePasswordViewModel
        initObserve()
    }

    fun initObserve(){
        revisePasswordViewModel.confirmResult.observe(this){
            when(it){
                TransformPasswordCode.TransformPassSuccess ->{
                    Common.showDialog(requireContext(),"변경 완료",TransformPasswordCode.TransformPassSuccess.comment)
                    findNavController().navigate(R.id.action_revisePasswordFragment_to_baseContainerFragment)
                }
                else -> Common.showDialog(requireContext(),"실패",it.comment)
            }
        }
    }

}