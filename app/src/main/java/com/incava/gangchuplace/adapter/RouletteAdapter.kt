package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemRouletteBinding
import com.incava.gangchuplace.model.RouletteMenuModel
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.viewmodel.RouletteViewModel

class RouletteAdapter(private val rouletteArray: MutableList<RouletteMenuModel> ) : RecyclerView.Adapter<RouletteAdapter.VH>() {
    //bind시 변수 붙여주기.
    inner class VH(val binding : ItemRouletteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RouletteMenuModel) {
//            binding.tvMenu.text = item.menuItem 뷰바인딩으로 되는지 확인 후 리펙토링
                binding.rouletteItem = item
                binding.rouletteViewModel = ViewModelProvider(binding.root.context as MainActivity).get(RouletteViewModel::class.java)
                binding.executePendingBindings()
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