package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.incava.gangchuplace.databinding.ItemGangchuPreviewBinding
import com.incava.gangchuplace.databinding.ItemMyHeartStoreBinding
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class MyHeartAdapter(val myHeartList : MutableList<GangChuPreview>) :
    RecyclerView.Adapter<MyHeartAdapter.VH>() {

    // 찜한 가게 리스트
    inner class VH(val binding: ItemMyHeartStoreBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemMyHeartStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = myHeartList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            gangChuPreview = myHeartList[position]
            gangChuVM = ViewModelProvider(holder.binding.root.context as MainActivity)[GangChuViewModel::class.java]
        }
    }
}