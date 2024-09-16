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
import androidx.navigation.fragment.findNavController
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
   private val animalCategoryAdapter by lazy{AnimalsCategoryAdapter()}
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

        binding.animalCategoryBack.setOnClickListener {
            findNavController().navigateUp()
        }

        animalCategoryAdapter.onClick = {animalCategory ->
            when(animalCategory.name) {
                "Carnivorous" -> {
                    animalViewModel.fetchAnimalList("carnivorous");
                    findNavController().navigate(R.id.action_animalCategoryFragment_to_carnivorousFragment)
                }
                "Herbivorous" -> {
                    animalViewModel.fetchAnimalList("herbivorous");
                    findNavController().navigate(R.id.action_animalCategoryFragment_to_herbivorousFragment)
                }
                "Omnivorous" -> {
                    animalViewModel.fetchAnimalList("omnivorous");
                    findNavController().navigate(R.id.action_animalCategoryFragment_to_omnivorousFragment)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            animalViewModel.animalCategoryList.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        //binding.animalCategoryProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        animalCategoryAdapter.differ.submitList(it.data)
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

    private fun setupRv() {
        binding.animalCategoryRv.apply {
            adapter = animalCategoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


}
