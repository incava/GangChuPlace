package com.incava.gangchuplace.view.main

import android.Manifest
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu) {


    val gangChuViewModel  : GangChuViewModel by activityViewModels()
    val uniqueId by lazy{
        getSharedPreference(requireContext()).run {
            "${loginRoute}+${email}"
        }
    }

    override fun init() {
        binding.gangChu = this
        binding.gangChuVM = gangChuViewModel
        //GPS 권한 요청 후,응답이 오면 uniqueId를 전송 하며 목록을 load
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        gangChuViewModel.requestGangChuList(uniqueId)
    }




}