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
import com.example.kidsapp.adapters.ColorsAdapter
import com.example.kidsapp.databinding.FragmentColorsBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.ColorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ColorsFragment : Fragment() {
    private lateinit var binding: FragmentColorsBinding
    private val colorsAdapter by lazy { ColorsAdapter() }
    private val colorsViewModel by viewModels<ColorsViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpColorsAdapter()

        binding.colorsBack.setOnClickListener{
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            colorsViewModel.colorsList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        //binding.colorsProgressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        colorsAdapter.differ.submitList(it.data)
                        //binding.colorslProgressBar.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        //binding.colorsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }


    }

    private fun setUpColorsAdapter() {
        binding.colorsRv.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

}