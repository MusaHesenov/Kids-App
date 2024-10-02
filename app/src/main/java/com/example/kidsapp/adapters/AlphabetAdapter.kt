package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.databinding.AlphabetRvItemBinding

class AlphabetAdapter (private val alphabets: List<String>,private val onItemClick:(String) -> Unit) : RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder>() {

    inner class AlphabetViewHolder(val binding: AlphabetRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(alphabet: String) {
            binding.apply {
                tvAlphabetItem.text = alphabet.toString()
            }
            itemView.setOnClickListener{
                onItemClick(alphabet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        return AlphabetViewHolder(AlphabetRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = alphabets.size

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        holder.bind(alphabets[position])
    }

}
