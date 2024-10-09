package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.ColorsAdapter
import com.example.kidsapp.adapters.JobsAdapter
import com.example.kidsapp.databinding.FragmentJobsBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.utils.VerticalItemDecoration
import com.example.kidsapp.viewmodel.ColorsViewModel
import com.example.kidsapp.viewmodel.JobsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class JobsFragment : Fragment() {
    private lateinit var binding : FragmentJobsBinding
    private val jobsAdapter by lazy { JobsAdapter() }
    private val jobsViewModel by viewModels<JobsViewModel> ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpColorsAdapter()

        binding.jobsBack.setOnClickListener{
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            jobsViewModel.jobsList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        //binding.jobsProgressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        jobsAdapter.differ.submitList(it.data)
                        //binding.jobsProgressBar.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        //binding.jobsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }

    }

    private fun setUpColorsAdapter() {
        binding.jobsRv.apply {
            adapter = jobsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(HorizontalItemDecoration())
            addItemDecoration(VerticalItemDecoration())
        }
    }

}