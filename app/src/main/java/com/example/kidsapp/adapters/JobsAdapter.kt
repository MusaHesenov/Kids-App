package com.example.kidsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidsapp.data.Fruits
import com.example.kidsapp.data.Jobs
import com.example.kidsapp.databinding.JobsRvBinding

class JobsAdapter : RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    inner class JobsViewHolder(val binding: JobsRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bin(jobs: Jobs?) {
            binding.apply {
                jobsName.text = jobs?.jobsName
                Glide.with(itemView).load(jobs?.image).into(jobsImage)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Jobs>() {
        override fun areItemsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
            return oldItem.jobsName == newItem.jobsName
        }

        override fun areContentsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
            return oldItem.jobsName == newItem.jobsName
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(JobsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val jobs = differ.currentList[position]
        holder.bin(jobs)
    }

}