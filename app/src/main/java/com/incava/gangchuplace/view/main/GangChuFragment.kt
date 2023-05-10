package com.incava.gangchuplace.view.main

import android.Manifest
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.util.Common.getSharedPreference
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu) {


    val gangChuViewModel: GangChuViewModel by activityViewModels()
    val uniqueId by lazy {
        getSharedPreference(requireContext()).run {
            "${loginRoute}+${email}"
        }
    }

    override fun init() {
        binding.gangChu = this
        binding.gangChuVM = gangChuViewModel
        attachArray()
        //GPS 권한 요청 후,응답이 오면 uniqueId를 전송 하며 목록을 load
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onResume() {
        super.onResume()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            gangChuViewModel.requestGangChuList(uniqueId)
        }

    //R.arrays.xml에 있는 배열을 AutoCompleteTextView 붙임
    private fun attachArray() {
        binding.spinner.apply {
            adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.gang_chu_filter)
            )
        }
    }


}