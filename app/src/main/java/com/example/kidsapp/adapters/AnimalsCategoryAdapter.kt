package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.data.AnimalCategory
import com.example.kidsapp.databinding.AnimalCategoryRvBinding

class AnimalsCategoryAdapter: RecyclerView.Adapter<AnimalsCategoryAdapter.AnimalsCategoryViewHolder>() {
    inner class AnimalsCategoryViewHolder(val binding : AnimalCategoryRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(animalCategory: AnimalCategory) {
            binding.apply {
                titleCategory.text = animalCategory.name
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<AnimalCategory>() {
        override fun areItemsTheSame(oldItem: AnimalCategory, newItem: AnimalCategory): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AnimalCategory, newItem: AnimalCategory): Boolean {
            return oldItem.name == newItem.name
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsCategoryViewHolder {
        return AnimalsCategoryViewHolder(AnimalCategoryRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimalsCategoryViewHolder, position: Int) {
        val animalCategory = differ.currentList[position]
        holder.bind(animalCategory)

        holder.itemView.setOnClickListener {
            onClick?.invoke(animalCategory)
        }

    }
    var onClick: ((AnimalCategory) -> Unit)? = null
}