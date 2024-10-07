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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.FruitsAdapter
import com.example.kidsapp.adapters.InstrumentsAdapter
import com.example.kidsapp.databinding.FragmentFruitsBinding
import com.example.kidsapp.databinding.FragmentInstrumentsBinding
import com.example.kidsapp.utils.HorizontalItemDecoration
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.utils.VerticalItemDecoration
import com.example.kidsapp.viewmodel.FruitsViewModel
import com.example.kidsapp.viewmodel.InstrumentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class InstrumentsFragment : Fragment() {

    private lateinit var binding: FragmentInstrumentsBinding
    private val instrumentsAdapter by lazy { InstrumentsAdapter() }
    private val instrumentsViewModel by viewModels<InstrumentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstrumentsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpInstrumentsAdapter()

        binding.instrumentsBack.setOnClickListener{
            findNavController().navigateUp()
        }


        lifecycleScope.launchWhenStarted {
            instrumentsViewModel.instrumentsList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        //binding.fruitsProgressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        instrumentsAdapter.differ.submitList(it.data)
                        //binding.shapeslProgressBar.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        //binding.fruitsProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
    }
    private fun setUpInstrumentsAdapter() {
        binding.instrumentsRv.apply {
            adapter = instrumentsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(HorizontalItemDecoration())
            addItemDecoration(VerticalItemDecoration())
        }
    }
}