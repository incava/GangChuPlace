package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyHeartBinding
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class MyHeartFragment : BaseFragment<FragmentMyHeartBinding>(R.layout.fragment_my_heart) {
    private val gangChuViewModel : GangChuViewModel by viewModels()
    override fun init() {
        binding.gangChuVM = gangChuViewModel
    }

}