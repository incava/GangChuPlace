package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyHeartBinding
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class MyHeartFragment : BaseFragment<FragmentMyHeartBinding>(R.layout.fragment_my_heart) {
    private val gangChuViewModel : GangChuViewModel by activityViewModels()
    val uniqueId by lazy{
        getSharedPreference(requireContext()).run {
            "${loginRoute}+${id}"
        }
    }
    override fun init() {
        binding.apply {
            gangChuVM = gangChuViewModel
            gangChuViewModel.requestHeartStoreList(uniqueId)
            toolbar.setupWithNavController(findNavController())
        }

    }

}