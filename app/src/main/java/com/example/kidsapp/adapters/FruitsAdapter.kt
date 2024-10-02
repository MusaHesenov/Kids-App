package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Fruits
import com.example.kidsapp.databinding.FruitsRvBinding

class FruitsAdapter: RecyclerView.Adapter<FruitsAdapter.FruitsViewHolder>() {

    inner class FruitsViewHolder(val binding: FruitsRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin(fruits: Fruits?){
            binding.apply {
                fruitsName.text = fruits?.fruitsName
                Glide.with(itemView).load(fruits?.image).into(fruitsImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Fruits>() {
        override fun areItemsTheSame(oldItem: Fruits, newItem: Fruits): Boolean {
            return oldItem.fruitsName == newItem.fruitsName
        }

        override fun areContentsTheSame(oldItem: Fruits, newItem: Fruits): Boolean {
            return oldItem.fruitsName == newItem.fruitsName
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsViewHolder {
        return FruitsViewHolder(FruitsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FruitsViewHolder, position: Int) {
        val fruits = differ.currentList[position]
        holder.bin(fruits)
    }
}