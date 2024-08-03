package com.example.kidsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kidsapp.R
import com.example.kidsapp.activities.HomeActivity
import com.example.kidsapp.databinding.FragmentAccountSuccessBinding
import com.example.kidsapp.databinding.FragmentHomeBinding

class AccountSuccessFragment: Fragment(R.layout.fragment_account_success){
    private lateinit var binding: FragmentAccountSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountSuccessBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}