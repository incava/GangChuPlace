package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRevisePasswordBinding
import com.incava.gangchuplace.viewmodel.MyInfoViewModel


class RevisePasswordFragment : BaseFragment<FragmentRevisePasswordBinding>(R.layout.fragment_revise_password) {
    private val myInfoViewModel : MyInfoViewModel by viewModels()
    override fun init() {
        binding.myInfoVM = myInfoViewModel
    }

}