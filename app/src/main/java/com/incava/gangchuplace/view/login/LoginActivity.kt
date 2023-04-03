package com.incava.gangchuplace.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.ActivityLoginBinding
import com.incava.gangchuplace.view.main.MainActivity

class LoginActivity : AppCompatActivity() {
    //private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.login = this
    }
    fun loginCheck(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}