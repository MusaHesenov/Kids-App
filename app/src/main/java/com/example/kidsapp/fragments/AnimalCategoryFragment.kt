package com.example.kidsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AnimalsCategoryAdapter
import com.example.kidsapp.databinding.FragmentAnimalCategoryBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


private val TAG = "AnimalCategoryFragment"
@AndroidEntryPoint
class AnimalCategoryFragment : Fragment() {
   private lateinit var binding: FragmentAnimalCategoryBinding
   private val animalAdapter by lazy{AnimalsCategoryAdapter()}
    private val animalViewModel by viewModels<AnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
    }

    private fun setupRv() {
        binding.animalCategoryRv.apply {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        lifecycleScope.launchWhenStarted {
            animalViewModel.animalCategoryList.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        //binding.animalCategoryProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        animalAdapter.differ.submitList(it.data)
                        //binding.animalCategoryProgressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        //binding.animalCategoryProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        Log.e(TAG, it.message.toString())
                    }
                    else -> Unit
                }
            }
        }
    }


}
