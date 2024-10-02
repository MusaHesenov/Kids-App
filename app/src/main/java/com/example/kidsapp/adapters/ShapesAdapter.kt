package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Animal
import com.example.kidsapp.data.Shapes
import com.example.kidsapp.databinding.ShapesRvBinding

class ShapesAdapter: RecyclerView.Adapter<ShapesAdapter.ShapesViewHolder>() {
    inner class ShapesViewHolder(val binding: ShapesRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bin(shapes:Shapes?) {
            binding.apply {
                shapesName.text = shapes?.shapesName
                Glide.with(itemView).load(shapes?.image).into(shapesImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Shapes>() {
        override fun areItemsTheSame(oldItem: Shapes, newItem: Shapes): Boolean {
            return oldItem.shapesName == newItem.shapesName
        }

        override fun areContentsTheSame(oldItem: Shapes, newItem: Shapes): Boolean {
            return oldItem.shapesName == newItem.shapesName
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapesViewHolder {
        return ShapesViewHolder(ShapesRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ShapesViewHolder, position: Int) {
        val shapes = differ.currentList[position]
        holder.bin(shapes)
    }
}