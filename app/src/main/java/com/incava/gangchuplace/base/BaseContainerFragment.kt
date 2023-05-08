package com.incava.gangchuplace.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.FragmentBaseContainerBinding

class BaseContainerFragment : Fragment() {
    private var _binding : FragmentBaseContainerBinding? = null
    private val binding get() = _binding!!

    //var time : Long = 0

    //lateinit var backPressedCallback : OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseContainerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this){
//            if (System.currentTimeMillis() - time < 3000){// 3초 안에 2번 back이 눌릴 시 시스템 종료.
//                requireActivity().finish()
//            }
//            time = System.currentTimeMillis() // 다시 time 조정.
//            Toast.makeText(requireContext(),"한번 더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show()
//        }
    initNavigation()
    }

    private fun initNavigation() {
        // NavController 획득
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.base_nav_host) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNav.setupWithNavController(navController)
    }


}