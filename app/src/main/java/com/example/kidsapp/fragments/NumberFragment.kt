package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.kidsapp.R
import com.example.kidsapp.adapters.NumberPagerAdapter
import com.example.kidsapp.databinding.FragmentNumberBinding

class NumberFragment: Fragment() {
    private lateinit var binding: FragmentNumberBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()

        binding.numberBack.setOnClickListener {
            findNavController().navigateUp()
        }
        //viewPager.setCurrentItem(9, true)
    }

    private fun setupViewPager() {
        viewPager = binding.viewPager
        val adapter = NumberPagerAdapter(this)
        viewPager.adapter = adapter
    }

}