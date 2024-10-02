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
import com.example.kidsapp.adapters.ShapesAdapter
import com.example.kidsapp.databinding.FragmentCategoryBinding
import com.example.kidsapp.databinding.FragmentShapesBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.ShapesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ShapesFragment : Fragment() {
    private lateinit var binding: FragmentShapesBinding
    private val shapesAdapter by lazy { ShapesAdapter() }
    private val shapesViewModel by viewModels<ShapesViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShapesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpShapesAdapter()

        binding.shapesBack.setOnClickListener{
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            shapesViewModel.shapesList.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        //binding.shapesProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        shapesAdapter.differ.submitList(it.data)
                        //binding.shapeslProgressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        //binding.shapesProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun setUpShapesAdapter() {
        binding.shapesRv.apply {
            adapter = shapesAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

}