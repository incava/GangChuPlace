package com.incava.gangchuplace.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.login = this
    }
    fun loginCheck(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}