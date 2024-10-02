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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.FruitsAdapter
import com.example.kidsapp.databinding.FragmentFruitsBinding
import com.example.kidsapp.databinding.FragmentShapesBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.FruitsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FruitsFragment : Fragment() {
    private lateinit var binding: FragmentFruitsBinding
    private val fruitsAdapter by lazy { FruitsAdapter() }
    private val fruitsViewModel by viewModels<FruitsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFruitsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFruitsAdapter()

        binding.fruitsBack.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            fruitsViewModel.fruitsList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        //binding.fruitsProgressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        fruitsAdapter.differ.submitList(it.data)
                        //binding.shapeslProgressBar.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        //binding.fruitsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun setUpFruitsAdapter() {
        binding.fruitsRv.apply {
            adapter = fruitsAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

}