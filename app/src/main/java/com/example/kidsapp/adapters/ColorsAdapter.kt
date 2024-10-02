package com.example.kidsapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Colors
import com.example.kidsapp.data.Fruits
import com.example.kidsapp.databinding.ColorsRvBinding

class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>(){

    inner class ColorsViewHolder(val binding: ColorsRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin(colors: Colors?){
            binding.apply {
                colorsName.text = colors?.colorsName
                Glide.with(itemView).load(colors?.image).into(colorsImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Colors>() {
        override fun areItemsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem.colorsName == newItem.colorsName
        }

        override fun areContentsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem.colorsName == newItem.colorsName
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        return ColorsViewHolder(ColorsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val colors = differ.currentList[position]
        holder.bin(colors)
    }
}