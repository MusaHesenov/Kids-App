package com.example.kidsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.OmnivorousAdapter
import com.example.kidsapp.databinding.FragmentOmnivorousBinding
import com.example.kidsapp.utils.Resource
import com.example.kidsapp.viewmodel.AnimalViewModel
import kotlinx.coroutines.flow.collectLatest

class OmnivorousFragment : Fragment() {
    private lateinit var binding : FragmentOmnivorousBinding
    private val omnivorousAdapter by lazy { OmnivorousAdapter() }
    private val animalViewModel: AnimalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentOmnivorousBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOmnivorousAdapter()

        binding.omnivorousBack.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenResumed {
            animalViewModel.animalList.collectLatest {
                Log.d("TAG", "Resource state: $it")
                when (it) {
                    is Resource.Loading -> {
                        //binding.animalProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty()) {
                            Log.d("TAG", "List is empty")
                        } else {
                            Log.d("TAG", "Submitting list: ${it.data}")
                            omnivorousAdapter.differ.submitList(it.data)
                        }
                        //binding.animalProgressBar.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        //binding.animalProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setUpOmnivorousAdapter() {
        binding.omnivorousRv.apply {
            adapter = omnivorousAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
    }


}
