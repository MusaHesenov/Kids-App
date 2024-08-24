package com.example.kidsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.ActivityAdapter
import com.example.kidsapp.adapters.CategoryAdapter
import com.example.kidsapp.data.Activity
import com.example.kidsapp.data.Category
import com.example.kidsapp.databinding.FragmentHomeBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.CategoryViewModel
import com.example.kidsapp.viewmodel.UserAccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var activityList: ArrayList<Activity>
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val activityAdapter by lazy { ActivityAdapter() }
    private val viewModel: UserAccountViewModel by activityViewModels()
    private val categoryViewModel by viewModels<CategoryViewModel>()

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

        setupCategoryRv()
        setupActivityRv()


        binding.viewAllCategory.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        binding.tvName.text = it.data?.firstName
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }

            }
            viewModel.getUser()
        }

        lifecycleScope.launchWhenStarted {
            categoryViewModel.categoryListLimit.collectLatest {
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

        lifecycleScope.launchWhenStarted {
            categoryViewModel.activityList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        activityAdapter.differ.submitList(it.data)
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

    private fun setupActivityRv() {
        binding.rvActivity.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = activityAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }

    private fun hideLoading() {
        binding.categoryProgressBar.visibility = View.GONE
        binding.bannerProgressBar.visibility = View.GONE
        binding.activityProgressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.categoryProgressBar.visibility = View.VISIBLE
        binding.bannerProgressBar.visibility = View.VISIBLE
        binding.activityProgressBar.visibility = View.VISIBLE
    }

    private fun setupCategoryRv() {
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }
}