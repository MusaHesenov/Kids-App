package com.example.kidsapp.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AgeAdapter
import com.example.kidsapp.data.User
import com.example.kidsapp.databinding.FragmentFillProfileBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.UserAccountViewModel
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FillProfileFragment : Fragment(R.layout.fragment_fill_profile) {
    private lateinit var binding: FragmentFillProfileBinding
    private var selectedImageView: ShapeableImageView? = null
    private lateinit var ageList: List<String>
    private val viewModel by viewModels<UserAccountViewModel>()
    private var imageUri : Uri? = null
    private var selectedAge: String? = null

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


        ageList = listOf("3", "4", "5", "6", "7", "8", "9", "10")
        val avatarUris = listOf(
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1"),
            Uri.parse("android.resource://com.example.kidsapp/drawable/avatar1")
        )

        val ageAdapter = AgeAdapter(ageList)

        binding.rvAge.apply {
            adapter = ageAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HorizontalItemDecoration())
        }

        ageAdapter.onItemClick = {
            selectedAge = it
        }

        val avatarGrid = binding.avatarGrid

        for (i in 0 until avatarGrid.childCount) {
            val imageView = avatarGrid.getChildAt(i) as ShapeableImageView
            imageView.setImageURI(avatarUris[i])
            imageView.tag = avatarUris[i]  // Assign the corresponding URI as the tag
            imageView.setOnClickListener {
                selectedImageView?.isSelected = false
                imageView.isSelected = true
                selectedImageView = imageView
            }
        }


        lifecycleScope.launchWhenStarted {
            viewModel.updateInfo.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.buttonSave.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.buttonSave.revertAnimation()
                        findNavController().navigate(R.id.action_fillProfileFragment_to_accountSuccessFragment)
                    }
                    is Resource.Error -> {
                        binding.buttonSave.revertAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.buttonSave.setOnClickListener {
            val selectedUri = selectedImageView?.tag as? Uri
            if (selectedUri != null) {
                viewModel.updateUser(selectedUri, selectedAge)
            } else {
                Toast.makeText(requireContext(), "Please select an avatar.", Toast.LENGTH_SHORT).show()
            }
        }


    }

}