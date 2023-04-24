package com.incava.gangchuplace.view.write

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentWriteSearchBinding
import com.incava.gangchuplace.viewmodel.WriteStoreViewModel
import com.incava.gangchuplace.viewmodel.WriteViewModel

class WriteSearchFragment : BaseFragment<FragmentWriteSearchBinding>(R.layout.fragment_write_search) {
    private val  writeStoreViewModel : WriteStoreViewModel by lazy {
        ViewModelProvider(this)[ WriteStoreViewModel::class.java]
    }
    override fun init() {
        binding.writeStoreVM = writeStoreViewModel
        binding.editSearch.requestFocus()
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editSearch, InputMethodManager.SHOW_IMPLICIT) // 키보드를 보여줍니다.
    }
}