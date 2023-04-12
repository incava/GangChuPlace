package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemReviewPreviewBinding
import com.incava.gangchuplace.viewmodel.DetailPageViewModel

class DetailAdapter(val vm: DetailPageViewModel) : RecyclerView.Adapter<DetailAdapter.VH>() {
    private val reviewInfoList = vm.detailPageList.value

    inner class VH(val binding: ItemReviewPreviewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemReviewPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = reviewInfoList?.size ?: 0

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            reviewInfo = reviewInfoList?.get(position) ?: return
            detailPageVM = vm
        }
    }
}