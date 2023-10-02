package com.incava.gangchuplace.view.main.friend

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentFriendBinding
import com.incava.gangchuplace.viewmodel.FriendViewModel

class FriendFragment : BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {
    private val friendViewModel: FriendViewModel by viewModels()
    override fun init() {
        binding.apply {
            friend = this@FriendFragment
            binding.friendVM = friendViewModel
        }
    }



}