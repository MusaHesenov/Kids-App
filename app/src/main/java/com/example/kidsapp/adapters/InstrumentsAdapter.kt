package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Fruits
import com.example.kidsapp.data.Instruments
import com.example.kidsapp.databinding.InstrumentsRvBinding

class InstrumentsAdapter: RecyclerView.Adapter<InstrumentsAdapter.InstrumentsViewHolder>() {

    inner class InstrumentsViewHolder(val binding: InstrumentsRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin(instruments: Instruments?){
            binding.apply {
                instrumentName.text = instruments?.name
                Glide.with(itemView).load(instruments?.image).into(instrumentImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Instruments>() {
        override fun areItemsTheSame(oldItem: Instruments, newItem: Instruments): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Instruments, newItem: Instruments): Boolean {
            return oldItem.name == newItem.name
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentsViewHolder {
        return InstrumentsViewHolder(InstrumentsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: InstrumentsViewHolder, position: Int) {
        val instruments = differ.currentList[position]
        holder.bin(instruments)
    }
}