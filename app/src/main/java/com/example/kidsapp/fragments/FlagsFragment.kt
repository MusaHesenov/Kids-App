package com.example.kidsapp.fragments

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
import com.example.kidsapp.R
import com.example.kidsapp.adapters.FlagsAdapter
import com.example.kidsapp.databinding.FragmentFlagsBinding
import com.example.kidsapp.databinding.FragmentShapesBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.FlagsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FlagsFragment : Fragment() {
    private lateinit var binding: FragmentFlagsBinding
    private val flagsAdapter by lazy { FlagsAdapter() }
    private val flagsViewModel by viewModels<FlagsViewModel> ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlagsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFlagsAdapter()

        binding.flagsBack.setOnClickListener{
            findNavController().navigateUp()
        }


        lifecycleScope.launchWhenStarted {
            flagsViewModel.flagsList.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        //binding.flagsProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        flagsAdapter.differ.submitList(it.data)
                        //binding.flagslProgressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        //binding.flagsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setUpFlagsAdapter() {
        binding.flagsRv.apply {
            adapter = flagsAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }


}