package com.incava.gangchuplace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.incava.gangchuplace.databinding.ItemWriteSearchBinding
import com.incava.gangchuplace.model.StorePlace
import com.incava.gangchuplace.view.main.MainActivity
import com.incava.gangchuplace.view.write.WriteSearchFragment
import com.incava.gangchuplace.viewmodel.WriteStoreViewModel
import com.incava.gangchuplace.viewmodel.WriteViewModel

class WriteAdapter(val vm : WriteStoreViewModel) :
    RecyclerView.Adapter<WriteAdapter.VH>() {

    private val placeList = vm.placeList.value
    inner class VH(val binding: ItemWriteSearchBinding) : RecyclerView.ViewHolder(binding.root)

    //코틀린스럽게 대입연산자 사용해 VH로 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemWriteSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = placeList?.size?:0

    override fun onBindViewHolder(holder: VH, position: Int) {
        //bind에서 처리.
        holder.binding.storePlace = placeList?.get(position)
        holder.binding.writeStoreVM = vm
    }


}