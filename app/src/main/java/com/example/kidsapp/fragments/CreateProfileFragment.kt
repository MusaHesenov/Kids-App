package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AgeAdapter
import com.example.kidsapp.databinding.FragmentCreateProfileBinding
import com.example.kidsapp.utils.HorizontalItemDecoration

class CreateProfileFragment: Fragment(R.layout.fragment_create_profile) {
    private lateinit var binding: FragmentCreateProfileBinding
    private lateinit var ageList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_createProfileFragment_to_fillProfileFragment)
        }
        ageList = listOf("3", "4", "5", "6", "7", "8", "9", "10")

        val ageAdapter = AgeAdapter(ageList)

        binding.rvAge.apply {
            adapter = ageAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HorizontalItemDecoration())
        }
    }
}