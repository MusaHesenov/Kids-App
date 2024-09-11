package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.OmnivorousAdapter
import com.example.kidsapp.databinding.FragmentOmnivorousBinding

class OmnivorousFragment : Fragment() {
private lateinit var binding : FragmentOmnivorousBinding
private val omnivorousAdapter by lazy { OmnivorousAdapter() }
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
    }

    private fun setUpOmnivorousAdapter() {
        binding.omnivorousRv.apply {
            adapter = omnivorousAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
    }


}
