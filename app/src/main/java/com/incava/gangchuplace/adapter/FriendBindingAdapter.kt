package com.incava.gangchuplace.adapter

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.incava.gangchuplace.view.main.friend.MyFriendFragment
import com.incava.gangchuplace.view.main.friend.RequestFriendFragment


/**
 * 친구 관련 바인딩 어댑터들을 모아놓은 오브젝트.
 */
object FriendBindingAdapter {
    // 2개로 정해 놓은 fragment
    private val fragmentList: MutableList<Fragment> =
        mutableListOf(MyFriendFragment(), RequestFriendFragment())
    private val tabList = mutableListOf("친구", "친구 요청")

    //Viewpager를 설정하는 어댑터
    @JvmStatic
    @BindingAdapter("setFriendPagerAdapter")
    fun setAdapter(view: ViewPager2, fragment: Fragment) {
        view.apply {
            adapter = FriendViewPagerAdapter(fragment, fragmentList)
        }
    }

    //Mediator를 설정하는 어댑터
    @JvmStatic
    @BindingAdapter("setMediator")
    fun setMediator(viewpager: ViewPager2, tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }


}