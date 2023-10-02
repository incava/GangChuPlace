package com.incava.gangchuplace.view.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 내비게이션 스택이 비어 있는지 확인
                val navController = findNavController(binding.mainNavHost)
                if (navController.graph.startDestinationId == navController.currentDestination?.id) {
                    // 내비게이션 스택이 비어 있으므로 앱 종료
                    Log.i("종료!", "앱 종료")
                    finish()
                } else {
                    // 내비게이션 스택이 비어 있지 않으므로 뒤로 가기 이벤트 전달
                    Log.i("종료!", "앱 종료x")
                    navController.navigateUp()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}