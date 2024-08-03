package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.data.Activity
import com.example.kidsapp.data.Category
import com.example.kidsapp.databinding.CategoryRvItemBinding

class ActivityAdapter(val activityList: List<Activity>): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    class ActivityViewHolder(val binding: CategoryRvItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(CategoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.binding.categoryImage.setImageResource(activityList[position].image)
        holder.binding.categoryName.text = activityList[position].title
        holder.binding.layout.setBackgroundResource(activityList[position].background)
    }
}