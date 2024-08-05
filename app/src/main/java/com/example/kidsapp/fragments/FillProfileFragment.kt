package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kidsapp.R
import com.example.kidsapp.databinding.FragmentFillProfileBinding
import com.google.android.material.imageview.ShapeableImageView

class FillProfileFragment : Fragment(R.layout.fragment_fill_profile) {
    private lateinit var binding: FragmentFillProfileBinding
    private var selectedImageView: ShapeableImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFillProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarGrid = binding.avatarGrid

        for (i in 0 until avatarGrid.childCount) {
            val imageView = avatarGrid.getChildAt(i) as ShapeableImageView
            imageView.setOnClickListener {
                selectedImageView?.isSelected = false
                imageView.isSelected = true
                selectedImageView = imageView
            }
        }

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_fillProfileFragment_to_accountSuccessFragment)
        }
    }
}