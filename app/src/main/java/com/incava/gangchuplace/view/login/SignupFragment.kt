package com.incava.gangchuplace.view.login

import androidx.fragment.app.viewModels
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentSignupBinding
import com.incava.gangchuplace.viewmodel.SignupViewModel


class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {
    private val signupViewModel : SignupViewModel by viewModels()
    override fun init() {
        binding.signupVM = signupViewModel
    }

}