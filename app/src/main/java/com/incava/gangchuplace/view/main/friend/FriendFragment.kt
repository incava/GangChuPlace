package com.incava.gangchuplace.view.main.friend

import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentFriendBinding

class FriendFragment : BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {
    override fun init() {
        binding.lifecycleOwner = this
        binding.friend = this
    }
}