package com.example.kidsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.CategoryAdapter
import com.example.kidsapp.databinding.FragmentCategoryBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.utils.VerticalItemDecoration
import com.example.kidsapp.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = "CategoryFragment"

@AndroidEntryPoint
class CategoryFragment: Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding
    private val categoryViewModel by viewModels<CategoryViewModel>()
    private val categoryAdapter by lazy { CategoryAdapter() }

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

        categoryAdapter.onClick = { category ->
            when (category.categoryName) {
                "Numbers" -> findNavController().navigate(R.id.action_categoryFragment_to_numberFragment)

            }
        }

        setupCategoryRv()

        lifecycleScope.launchWhenStarted {
            categoryViewModel.categoryList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        categoryAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Log.e(TAG, it.message.toString())
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun hideLoading() {
        binding.categoryProgressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.categoryProgressBar.visibility = View.VISIBLE
    }

    private fun setupCategoryRv() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
            addItemDecoration(HorizontalItemDecoration())
            addItemDecoration(VerticalItemDecoration())
        }
    }
}