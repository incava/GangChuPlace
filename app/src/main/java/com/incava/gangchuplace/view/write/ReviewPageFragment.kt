package com.incava.gangchuplace.view.write

import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.incava.gangchuplace.R
import com.incava.gangchuplace.base.BaseFragment
import com.incava.gangchuplace.databinding.FragmentReviewPageBinding
import com.incava.gangchuplace.model.ReviewInfo


class ReviewPageFragment : BaseFragment<FragmentReviewPageBinding>(R.layout.fragment_review_page) {
    private val args: ReviewPageFragmentArgs by navArgs()
    val item: ReviewInfo by lazy {
        Gson().fromJson(args.reviewInfo,ReviewInfo::class.java)
    }
    override fun init() {
        binding.reviewInfo = item
    }

}