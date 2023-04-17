package com.incava.gangchuplace.view.main.friend

import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRequestFriendBinding
import com.incava.gangchuplace.viewmodel.FriendViewModel


class RequestFriendFragment : BaseFragment<FragmentRequestFriendBinding>(R.layout.fragment_request_friend) {
    override fun init() {
        // 자동으로  해주는 AACviewModel말고 직접 provider로 받기
        val vm: FriendViewModel = ViewModelProvider(this)[FriendViewModel::class.java]
        binding.apply {
            binding.friendVM = vm
        }
    }
}