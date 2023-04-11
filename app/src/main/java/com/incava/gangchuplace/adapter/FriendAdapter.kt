package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.ItemFriendBinding
import com.incava.gangchuplace.databinding.ItemMyRequestFriendBinding
import com.incava.gangchuplace.databinding.ItemRequestFriendBinding
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.FriendViewModel

class FriendAdapter(private val friendList: MutableList<User>, val type: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        when (type) {
            0 -> { // 친구 목록
                val binding =
                    ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FriendVH(binding)
            }

            1 -> { // 친구 요청 받은 목록
                val binding =
                    ItemRequestFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RequestFriendVH(binding)
            }

            2 -> { // 내가 요청한 친구 목록
                val binding =
                    ItemMyRequestFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyRequestFriendVH(binding)
            }
            else ->{ // 이외가 있을경우 에러.
                throw Exception("error")
            }
        }
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(type){
            0->(holder as FriendVH).bind(friendList[position])
            1->(holder as RequestFriendVH).bind(friendList[position])
            2->(holder as MyRequestFriendVH).bind(friendList[position])
            else-> throw Exception("error")
        }
    }

    inner class FriendVH(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                binding.user = user
                binding.friendVM = ViewModelProvider(binding.root.context as MainActivity)[FriendViewModel::class.java]
            }
            binding.executePendingBindings() // 바인딩의 업데이트를 위함.
        }
    }
    inner class RequestFriendVH(val binding: ItemRequestFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                binding.user = user
                binding.friendVM = ViewModelProvider(binding.root.context as MainActivity)[FriendViewModel::class.java]
            }
            binding.executePendingBindings() // 바인딩의 업데이트를 위함.
        }
    }
    inner class MyRequestFriendVH(val binding: ItemMyRequestFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                binding.user = user
                binding.friendVM = ViewModelProvider(binding.root.context as MainActivity)[FriendViewModel::class.java]
            }
            binding.executePendingBindings() // 바인딩의 업데이트를 위함.
        }
    }
}