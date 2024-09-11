package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.CarnivorousAdapter
import com.example.kidsapp.databinding.FragmentCarnivorousBinding

class CarnivorousFragment : Fragment(R.layout.fragment_carnivorous) {
    private lateinit var binding: FragmentCarnivorousBinding
    private val carnivorousAdapter by lazy { CarnivorousAdapter() }

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
    }

    private fun setUpCarnivorousAdapter(){
        binding.carnivorousRv.apply {
            adapter = carnivorousAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }
    
}