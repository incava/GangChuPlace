package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.incava.gangchuplace.R
import com.incava.gangchuplace.databinding.ItemFriendBinding
import com.incava.gangchuplace.model.User
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.FriendViewModel

class FriendAdapter(private val friendList : MutableList<User>,val txt : String) :
    RecyclerView.Adapter<FriendAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.VH {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding,parent)
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(friendList[position])
    }


    inner class VH(val binding : ItemFriendBinding,val parent: ViewGroup) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                binding.user = user
                binding.friendVM = ViewModelProvider(binding.root.context as MainActivity)[FriendViewModel::class.java]
                binding.btnRemove.text = txt // 이름만 바꾸 도록.
            }
            binding.executePendingBindings() // 바인딩의 업데이트를 위함.
        }
    }

}