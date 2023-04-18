package com.incava.gangchuplace.view.login

import androidx.fragment.app.viewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentLoginBinding
import com.incava.gangchuplace.viewmodel.LoginViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun init() {
        binding.loginVM = loginViewModel
    }

}