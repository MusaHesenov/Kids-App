package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kidsapp.R
import com.example.kidsapp.databinding.FragmentAnimalCategoryBinding


class AnimalCategoryFragment : Fragment() {
   private lateinit var binding: FragmentAnimalCategoryBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalCategoryBinding.inflate(inflater)
        return binding.root
    }


}
