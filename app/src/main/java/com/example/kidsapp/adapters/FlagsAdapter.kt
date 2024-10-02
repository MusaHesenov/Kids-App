package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Flags
import com.example.kidsapp.databinding.FlagsRvBinding

class FlagsAdapter: RecyclerView.Adapter<FlagsAdapter.FlagsViewHolder>() {
    inner class FlagsViewHolder(val binding : FlagsRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin(flags: Flags?) {
            binding.apply {
                flagsName.text = flags?.flagsName
                Glide.with(itemView).load(flags?.image).into(flagsImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Flags>() {
        override fun areItemsTheSame(oldItem: Flags, newItem: Flags): Boolean {
            return oldItem.flagsName == newItem.flagsName
        }

        override fun areContentsTheSame(oldItem: Flags, newItem: Flags): Boolean {
            return oldItem.flagsName == newItem.flagsName
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlagsViewHolder {
        return FlagsViewHolder(FlagsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FlagsViewHolder, position: Int) {
        val flags = differ.currentList[position]
        holder.bin(flags)
    }


}