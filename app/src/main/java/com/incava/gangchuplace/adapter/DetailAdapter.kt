package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemReviewPreviewBinding
import com.incava.gangchuplace.model.ReviewInfo
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.DetailPageViewModel

class DetailAdapter(val reviewInfoList : MutableList<ReviewInfo>) : RecyclerView.Adapter<DetailAdapter.VH>() {

    inner class VH(val binding: ItemReviewPreviewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemReviewPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = reviewInfoList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            reviewInfo = reviewInfoList[position]
            detailPageVM = ViewModelProvider(holder.binding.root.context as MainActivity)[DetailPageViewModel::class.java]
        }
    }
}