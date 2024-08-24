package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Animal
import com.example.kidsapp.databinding.AnimalRvItemBinding

class AnimalAdapter: RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    inner class AnimalViewHolder(val binding: AnimalRvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bin(animal: Animal?) {
            binding.apply {
                titleTv.text = animal?.title
                descriptionTv.text = animal?.description
                Glide.with(itemView).load(animal?.image).into(image)
            }
        }

    }

    private val differCallBack = object : DiffUtil.ItemCallback<Animal>(){
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(AnimalRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = differ.currentList[position]
        holder.bin(animal)
    }


}