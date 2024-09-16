package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Animal
import com.example.kidsapp.databinding.AnimalRvItemBinding

class CarnivorousAdapter: RecyclerView.Adapter<CarnivorousAdapter.CarnivorousViewHolder>(){
    inner class CarnivorousViewHolder(val binding: AnimalRvItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin2(animal: Animal?) {
            binding.apply {
                titleTv.text = animal?.name
                descriptionTv.text = animal?.description
                Glide.with(itemView).load(animal?.image).into(image)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.name == newItem.name
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarnivorousViewHolder {
        return CarnivorousViewHolder(AnimalRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CarnivorousViewHolder, position: Int) {
        val carnivorous = differ.currentList[position]
        holder.bin2(carnivorous)
    }
}