package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.HerbivorousAdapter
import com.example.kidsapp.databinding.FragmentHerbivorousBinding

class HerbivorousFragment : Fragment(R.layout.fragment_herbivorous) {
  private lateinit var binding : FragmentHerbivorousBinding
  private val herbivorousAdapter by lazy { HerbivorousAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHerbivorousBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpHerbivorousAdapter()

        binding.herbivorousBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpHerbivorousAdapter() {
        binding.herbivorousRv.apply {
            adapter = herbivorousAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }
}