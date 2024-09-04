package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Activity
import com.example.kidsapp.databinding.CategoryRvItemBinding

class ActivityAdapter: RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(val binding: CategoryRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: Activity) {
            binding.apply {
                Glide.with(itemView).load(activity.image).into(categoryImage)
                categoryName.text = activity.categoryName

            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Activity>(){
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.image == newItem.image
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(CategoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = differ.currentList[position]

        holder.bind(activity)

        holder.itemView.setOnClickListener {
            onClick?.invoke(activity)
        }
    }

    var onClick: ((Activity) -> Unit)? = null
}