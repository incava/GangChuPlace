package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyReviewBinding
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.viewmodel.DetailPageViewModel


class MyReviewFragment : BaseFragment<FragmentMyReviewBinding>(R.layout.fragment_my_review) {
    private val detailPageViewModel: DetailPageViewModel by activityViewModels()
    override fun init() {
        binding.apply {
            detailPageVM = detailPageViewModel
            // storePlace를 전달.
            detailPageViewModel.setUser(User("인간","","ingi1118@naver.com","카카오톡","123123"))
            toolbar.setupWithNavController(findNavController())
        }
    }
}