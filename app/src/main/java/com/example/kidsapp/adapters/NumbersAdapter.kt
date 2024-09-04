package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.R
import com.example.kidsapp.databinding.NumberRvItemBinding

class NumbersAdapter(
    private val numbers: List<Int>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    inner class NumberViewHolder(val binding: NumberRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Int) {
            binding.apply {
                tvNumberItem.text = number.toString()
            }
            itemView.setOnClickListener {
                onItemClick(number)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(NumberRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(numbers[position])
    }

    override fun getItemCount(): Int = numbers.size
}
