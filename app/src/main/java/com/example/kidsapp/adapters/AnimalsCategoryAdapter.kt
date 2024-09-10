package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.data.Animal
import com.example.kidsapp.databinding.AnimalCategoryRvBinding

class AnimalsCategoryAdapter: RecyclerView.Adapter<AnimalsCategoryAdapter.AnimalsCatergoryViewHolder>() {
    inner class AnimalsCatergoryViewHolder(val binding : AnimalCategoryRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin5(animalCategory: Animal) {
            binding.apply {
                titleCategory.text = animalCategory?.title

            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsCatergoryViewHolder {
        return AnimalsCatergoryViewHolder(AnimalCategoryRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimalsCatergoryViewHolder, position: Int) {
        val animalCategory = differ.currentList[position]
        holder.bin5(animalCategory)
    }
}