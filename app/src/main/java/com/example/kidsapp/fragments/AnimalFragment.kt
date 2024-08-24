package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AnimalAdapter
import com.example.kidsapp.databinding.FragmentAnimalBinding

class AnimalFragment: Fragment(R.layout.fragment_animal) {
    private lateinit var binding: FragmentAnimalBinding
    private val animalAdapter by lazy { AnimalAdapter() }

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
    }

    private fun setupAnimalAdapter() {
        binding.animalRv.apply {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}