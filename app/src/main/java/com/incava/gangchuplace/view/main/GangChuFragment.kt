package com.incava.gangchuplace.view.main

import android.content.Intent
import android.util.Log
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentGangChuBinding
import com.incava.gangchuplace.view.search.SearchActivity
import com.incava.gangchuplace.view.write.WriteActivity
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuFragment : BaseFragment<FragmentGangChuBinding>(R.layout.fragment_gang_chu){

    override fun init() {
        binding.gangChu = this
        binding.gangChuVM = ViewModelProvider(requireActivity())[GangChuViewModel::class.java]
    }
}