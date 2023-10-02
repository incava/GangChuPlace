package com.incava.gangchuplace.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.viewmodel.DetailPageViewModel
import com.incava.gangchuplace.viewmodel.GangChuViewModel
import com.incava.gangchuplace.viewmodel.WriteStoreViewModel

object RecyclerViewBindingAdapter {

    // DetailAdapter를 연결하는 바인딩 어댑터
    @JvmStatic
    @BindingAdapter("setDetailAdapter")
    fun setDetailAdapter(view: RecyclerView, items : MutableList<MyReviewInfo>) {
        view.apply {
            adapter = DetailAdapter(items)
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyReviewAdapter")
    fun setMyReviewAdapter(view: RecyclerView, items : MutableList<MyReviewInfo>) {
        view.apply {
            adapter = MyReviewAdapter(items)
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyHeartAdapter")
    fun setMyHeartAdapter(view: RecyclerView, items: MutableList<GangChuPreview>) {
        view.apply {
            adapter = MyHeartAdapter(items)
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    @JvmStatic
    @BindingAdapter("setWriteStoreAdapter")
    fun setWriteStoreAdapter(view: RecyclerView, items: MutableList<StorePlace>) {
        view.apply {
            adapter = WriteAdapter(items)
            layoutManager = LinearLayoutManager(view.context)
        }
    }


    @JvmStatic
    @BindingAdapter("recyclerAdapter")
    //리사이클러뷰와 ViewModel을 이어줄 바인딩 어댑터
    fun recyclerAdapter(view: RecyclerView, items: Any) {
        view.apply {
            if (items is MutableList<*>) {
                if (items.all { it is RouletteMenuModel }) {
                    adapter = RouletteAdapter(items as MutableList<RouletteMenuModel>)
                    layoutManager = LinearLayoutManager(view.context)
                } else if (items.all { it is GangChuPreview }) {
                    adapter = GangChuAdapter(items as MutableList<GangChuPreview>)
                    layoutManager =
                        GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
                }
            }
        }
    }

    //FriendFragment를 연결하는 어댑터
    @JvmStatic
    @BindingAdapter(value = ["setFriendAdapter", "friendType"], requireAll = true)
    //리사이클러뷰와 ViewModel을 이어줄 바인딩 어댑터
    fun setFriendAdapter(view: RecyclerView, item: MutableList<User>, txt: Int) {
        view.apply {
            layoutManager = LinearLayoutManager(view.context)
            val friendAdapter = FriendAdapter(txt)
            friendAdapter.setHasStableIds(true)
            // 깜빡임 제거
            adapter = friendAdapter
            (view.adapter as FriendAdapter).friendList = item
        }
    }

}