package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsapp.databinding.AgeRvItemBinding

class AgeAdapter(val agesList: List<String>): RecyclerView.Adapter<AgeAdapter.AgeViewHolder>() {

    private var selectedPosition = -1

    class AgeViewHolder(val binding: AgeRvItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeViewHolder {
        return AgeViewHolder(AgeRvItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return agesList.size
    }

    override fun onBindViewHolder(holder: AgeViewHolder, position: Int) {
        val age = agesList[position]

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)

            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(age)
        }

        holder.binding.tvSize.text = age

        if (position == selectedPosition){
            holder.binding.imageShadow.visibility = View.VISIBLE
        }else{
            holder.binding.imageShadow.visibility = View.INVISIBLE
        }
    }

    var onItemClick : ((String) -> Unit)? = null
}