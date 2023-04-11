package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemWriteSearchBinding
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.write.WriteActivity
import com.incava.gangchuplace.viewmodel.WriteViewModel

class WriteAdapter(private val placeList: MutableList<StorePlace>) :
    RecyclerView.Adapter<WriteAdapter.VH>() {
    inner class VH(val binding: ItemWriteSearchBinding) : RecyclerView.ViewHolder(binding.root)

    //코틀린스럽게 대입연산자 사용해 VH로 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemWriteSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        //bind에서 처리.
        holder.binding.storePlace = placeList[position]
        holder.binding.writeVM =
            ViewModelProvider(holder.binding.root.context as WriteActivity)[WriteViewModel::class.java]
    }


}