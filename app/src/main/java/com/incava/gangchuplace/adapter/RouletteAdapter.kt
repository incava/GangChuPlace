package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemRouletteBinding
import com.incava.gangchuplace.model.RouletteMenuModel

class RouletteAdapter : RecyclerView.Adapter<RouletteAdapter.VH>() {
    var rouletteArray = mutableListOf<RouletteMenuModel>()

    //bind시 변수 붙여주기.
    inner class VH(val binding : ItemRouletteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RouletteMenuModel) {
//            binding.tvMenu.text = item.menuItem 뷰바인딩으로 되는지 확인 후 리펙토링
                binding.rouletteItem = item
        }
    }

    //생성시 레이아웃 inflate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : VH {
        var binding = ItemRouletteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }



    override fun getItemCount() = rouletteArray.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(rouletteArray[position])
    }
}