package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kidsapp.R
import com.example.kidsapp.databinding.FragmentCreateProfileBinding

class CreateProfileFragment: Fragment(R.layout.fragment_create_profile) {
    private lateinit var binding: FragmentCreateProfileBinding

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
    }
}