package com.incava.gangchuplace.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FriendViewPagerAdapter(
    fragment: Fragment,
    val fragmentList: MutableList<Fragment> // fragment가 담긴 리스트.
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}