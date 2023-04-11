package com.incava.gangchuplace.view.write

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentWriteSearchBinding
import com.incava.gangchuplace.viewmodel.WriteViewModel

class WriteSearchFragment : BaseFragment<FragmentWriteSearchBinding>(R.layout.fragment_write_search) {
    private val writeViewModel : WriteViewModel by lazy {
        ViewModelProvider(requireActivity())[WriteViewModel::class.java]
    }
    override fun init() {
        binding.writeVM = writeViewModel
    }
}