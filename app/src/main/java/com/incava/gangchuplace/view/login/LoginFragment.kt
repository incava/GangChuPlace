package com.incava.gangchuplace.view.login

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.adapter.Common
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentLoginBinding
import com.incava.gangchuplace.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application = requireActivity().application))[LoginViewModel::class.java] }

    override fun init() {
        binding.loginVM = loginViewModel
        binding.loginFragment = this
        observerInit()
    }

    private fun observerInit() {
        loginViewModel.apply {
            checkLogin(viewLifecycleOwner, observer = {
                if (it) {//로그인 성공시
                    Toast.makeText(requireContext(), "로그인 성공!", Toast.LENGTH_SHORT).show()
                    moveHome()
                } else {//로그인 실패시
                    Common.showDialog(requireContext(), "로그인 실패", "아이디 또는 비밀번호를 다시 한번 확인해 주세요.")
                }
            })
        }
    }

    private fun moveHome() {
        findNavController().apply {
            graph.setStartDestination(R.id.baseContainerFragment)// nav graph의 처음 도착지를 변경
            navigate(R.id.action_loginFragment_to_baseContainerFragment) // home화면으로 이동.
        }
    }

    //회원가입 화면으로 단순 이동
    fun moveSignup() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment) // home화면으로 이동.
    }


}