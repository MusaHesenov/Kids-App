package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AnimalAdapter
import com.example.kidsapp.databinding.FragmentAnimalBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AnimalFragment: Fragment(R.layout.fragment_animal) {
    private lateinit var binding: FragmentAnimalBinding
    private val animalAdapter by lazy { AnimalAdapter() }
    private val animalViewModel by viewModels<AnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAnimalAdapter()

        lifecycleScope.launchWhenStarted {
            animalViewModel.animalList.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        //binding.animalProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        animalAdapter.differ.submitList(it.data)
                        //binding.animalProgressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        //binding.animalProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }


    }

    private fun setupAnimalAdapter() {
        binding.animalRv.apply {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}