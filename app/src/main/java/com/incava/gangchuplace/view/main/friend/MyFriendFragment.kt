package com.incava.gangchuplace.view.main.friend

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyFriendBinding
import com.incava.gangchuplace.viewmodel.FriendViewModel


class MyFriendFragment : BaseFragment<FragmentMyFriendBinding>(R.layout.fragment_my_friend) {
    override fun init() {

        // 자동으로  해주는 AACviewModel말고 직접 provider로 받기
        val vm : FriendViewModel = ViewModelProvider(this)[FriendViewModel::class.java]
        binding.apply {
            binding.friendVM = vm
        }
    }
}