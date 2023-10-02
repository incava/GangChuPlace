package com.incava.gangchuplace.view.main.friend

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentRequestFriendBinding
import com.incava.gangchuplace.viewmodel.FriendViewModel


class RequestFriendFragment : BaseFragment<FragmentRequestFriendBinding>(R.layout.fragment_request_friend) {
    private val friendViewModel: FriendViewModel by activityViewModels()
    override fun init() {
        // 자동으로  해주는 AACviewModel말고 직접 provider로 받기
        binding.apply {
            binding.friendVM = friendViewModel
        }
    }
}