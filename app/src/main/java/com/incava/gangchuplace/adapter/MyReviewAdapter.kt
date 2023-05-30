package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.incava.gangchuplace.databinding.ItemMyReviewBinding
import com.incava.gangchuplace.model.MyReviewInfo
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.MyReviewViewModel

class MyReviewAdapter(val reviewInfoList: MutableList<MyReviewInfo>) :
    RecyclerView.Adapter<MyReviewAdapter.VH>() {
    inner class VH(val binding: ItemMyReviewBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemMyReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = reviewInfoList.size


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            myReviewInfo = reviewInfoList[position]
            myReviewVM =
                ViewModelProvider(holder.binding.root.context as MainActivity)[MyReviewViewModel::class.java]
        }
    }
}