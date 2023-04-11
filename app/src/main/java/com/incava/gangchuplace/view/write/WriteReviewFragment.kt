package com.incava.gangchuplace.view.write

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentWriteReviewBinding
import com.incava.gangchuplace.viewmodel.WriteViewModel


class WriteReviewFragment :
    BaseFragment<FragmentWriteReviewBinding>(R.layout.fragment_write_review) {

    private val writeViewModel: WriteViewModel by lazy {
        ViewModelProvider(requireActivity())[WriteViewModel::class.java]
    }

    override fun init() {
        binding.writeVM = writeViewModel
    }

}