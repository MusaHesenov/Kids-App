package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.CategoriesAdapter
import com.example.kidsapp.data.Activity
import com.example.kidsapp.data.Category
import com.example.kidsapp.databinding.FragmentCategoryBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.VerticalItemDecoration

class CategoryFragment: Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryList: ArrayList<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryBack.setOnClickListener {
            findNavController().navigateUp()
        }

        categoryList = ArrayList()

        val animal = Category("Animals", R.drawable.img, R.drawable.category_background)
        val fruits = Category("Fruits", R.drawable.img, R.drawable.category_background_green)
        val vegetables = Category("Vegetables", R.drawable.img, R.drawable.category_background_blue)
        val story = Category("Stories", R.drawable.img, R.drawable.category_background_red)
        val quiz = Category("Quiz", R.drawable.img, R.drawable.category_background_purple)
        val animal1 = Category("Animals", R.drawable.img, R.drawable.category_background)
        val fruits2 = Category("Fruits", R.drawable.img, R.drawable.category_background_green)
        val vegetables1 = Category("Vegetables", R.drawable.img, R.drawable.category_background_blue)
        val story1 = Category("Stories", R.drawable.img, R.drawable.category_background_red)
        val quiz1 = Category("Quiz", R.drawable.img, R.drawable.category_background_purple)


        categoryList.add(animal)
        categoryList.add(fruits)
        categoryList.add(vegetables)
        categoryList.add(story)
        categoryList.add(quiz)
        categoryList.add(animal1)
        categoryList.add(fruits2)
        categoryList.add(vegetables1)
        categoryList.add(story1)
        categoryList.add(quiz1)

        val adapter = CategoriesAdapter(categoryList)

        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            this.adapter = adapter
            addItemDecoration(HorizontalItemDecoration())
            addItemDecoration(VerticalItemDecoration())
        }


    }
}