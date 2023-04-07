package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.incava.gangchuplace.databinding.ItemGangchuPreviewBinding
import com.incava.gangchuplace.model.GangChuPreview

class GangChuAdapter(val gangChuList : MutableList<GangChuPreview>) : RecyclerView.Adapter<GangChuAdapter.VH>() {

    inner class VH(val binding : ItemGangchuPreviewBinding) : ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemGangchuPreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount() = gangChuList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {

        }
    }
}