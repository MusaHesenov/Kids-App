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
import com.example.kidsapp.adapters.CarnivorousAdapter
import com.example.kidsapp.databinding.FragmentCarnivorousBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
@AndroidEntryPoint
class CarnivorousFragment : Fragment(R.layout.fragment_carnivorous) {
    private lateinit var binding: FragmentCarnivorousBinding
    private val carnivorousAdapter by lazy { CarnivorousAdapter() }
    private val animalViewModel by viewModels<AnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarnivorousBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCarnivorousAdapter()

        binding.carnivorousBack.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            animalViewModel.animalList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        //binding.animalProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty()) {
                            Log.d("TAG", "List is empty")
                        } else {
                            Log.d("TAG", "Submitting list: ${it.data}")
                            carnivorousAdapter.differ.submitList(it.data)
                        }
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

    private fun setUpCarnivorousAdapter(){
        binding.carnivorousRv.apply {
            adapter = carnivorousAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }
    
}