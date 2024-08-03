package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.ActivityAdapter
import com.example.kidsapp.adapters.CatregoryAdapter
import com.example.kidsapp.data.Activity
import com.example.kidsapp.data.Category
import com.example.kidsapp.databinding.ActivityHomeBinding
import com.example.kidsapp.databinding.FragmentHomeBinding
import com.example.kidsapp.utils.HorizontalItemDecoration

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var activityList: ArrayList<Activity>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList = ArrayList()
        activityList = ArrayList()

        val animal = Category("Animals", R.drawable.img, R.drawable.category_background)
        val fruits = Category("Fruits", R.drawable.img, R.drawable.category_background_green)
        val vegetables = Category("Vegetables", R.drawable.img, R.drawable.category_background_blue)

        val story = Activity("Stories", R.drawable.img, R.drawable.category_background_red)
        val quiz = Activity("Quiz", R.drawable.img, R.drawable.category_background_purple)

        categoryList.add(animal)
        categoryList.add(fruits)
        categoryList.add(vegetables)

        activityList.add(story)
        activityList.add(quiz)

        val adapter = CatregoryAdapter(categoryList)
        val activityAdapter = ActivityAdapter(activityList)

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            addItemDecoration(HorizontalItemDecoration())
        }

        binding.rvActivity.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = activityAdapter
            addItemDecoration(HorizontalItemDecoration())
        }


    }
}