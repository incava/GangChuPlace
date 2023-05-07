package com.incava.gangchuplace.view.main.info

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentMyReviewBinding
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.util.Common
import com.incava.gangchuplace.viewmodel.DetailPageViewModel
import com.incava.gangchuplace.viewmodel.MyReviewViewModel


class MyReviewFragment : BaseFragment<FragmentMyReviewBinding>(R.layout.fragment_my_review) {
    private val myReviewViewModel: MyReviewViewModel by viewModels()
    val uniqueId by lazy{
        Common.getSharedPreference(requireContext()).run {
            "${loginRoute}+${email}"
        }
    }
    override fun init() {
        binding.apply {
            myReviewVM = myReviewViewModel
            // storePlace를 전달.
            myReviewViewModel.requestMyReview(uniqueId)
            toolbar.setupWithNavController(findNavController())
        }
    }
}