package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentReviseInfoBinding
import com.incava.gangchuplace.viewmodel.MyInfoViewModel

class ReviseInfoFragment : BaseFragment<FragmentReviseInfoBinding>(R.layout.fragment_revise_info) {
    private val myInfoViewModel: MyInfoViewModel by activityViewModels()
    override fun init() {
        binding.myInfoVM = myInfoViewModel
    }

}