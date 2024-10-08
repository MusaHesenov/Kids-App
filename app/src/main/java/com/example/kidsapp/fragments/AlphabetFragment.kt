package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AlphabetAdapter
import com.example.kidsapp.adapters.AlphabetPagerAdapter
import com.example.kidsapp.databinding.FragmentAlphabetBinding


class AlphabetFragment : Fragment() {
    private lateinit var binding : FragmentAlphabetBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAlphabetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()

        binding.alphabetBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun setUpViewPager() {
        viewPager = binding.viewPager
        val adapter = AlphabetPagerAdapter(this)
        viewPager.adapter = adapter
    }

}