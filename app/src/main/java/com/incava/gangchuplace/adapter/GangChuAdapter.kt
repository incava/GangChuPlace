package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.incava.gangchuplace.databinding.ItemGangchuPreviewBinding
import com.incava.gangchuplace.model.GangChuPreview
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.GangChuViewModel

class GangChuAdapter(val gangChuList: MutableList<GangChuPreview>) :
    RecyclerView.Adapter<GangChuAdapter.VH>() {

    inner class VH(val binding: ItemGangchuPreviewBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemGangchuPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = gangChuList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            gangChuPreview = gangChuList[position]
            gangChuVM = ViewModelProvider(root.context as MainActivity)[GangChuViewModel::class.java]
        }
    }
}