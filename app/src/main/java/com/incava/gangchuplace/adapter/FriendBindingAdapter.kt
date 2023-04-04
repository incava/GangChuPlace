package com.incava.gangchuplace.adapter

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.incava.gangchuplace.R
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.friend.MyFriendFragment
import com.incava.gangchuplace.view.main.friend.RequestFriendFragment
import de.hdodenhof.circleimageview.CircleImageView


/**
 * 친구 관련 바인딩 어댑터들을 모아놓은 오브젝트.
 */
object FriendBindingAdapter {
    // 2개로 정해 놓은 fragment
    private val fragmentList : MutableList<Fragment> = mutableListOf(MyFriendFragment(), RequestFriendFragment())
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

    @JvmStatic
    @BindingAdapter(value=["setFriendAdapter","friendType"], requireAll = true)
    //리사이클러뷰와 ViewModel을 이어줄 바인딩 어댑터
    fun setFriendAdapter(view: RecyclerView, item : MutableList<User>, txt : Int) {
        view.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = FriendAdapter(item, txt)
        }
    }
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view : CircleImageView, url : String){
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.vector_thumb_up)
            .into(view)
    }



}