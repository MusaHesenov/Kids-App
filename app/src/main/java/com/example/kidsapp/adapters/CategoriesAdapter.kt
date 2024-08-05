package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.data.Category
import com.example.kidsapp.databinding.CategoryRvItemBinding

class CategoriesAdapter(val categoryList: List<Category>): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: CategoryRvItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.categoryImage.setImageResource(categoryList[position].image)
        holder.binding.categoryName.text = categoryList[position].title
        holder.binding.layout.setBackgroundResource(categoryList[position].background)
    }

    var onClick: ((Category) -> Unit)? = null
}