package com.example.kidsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.kidsapp.R
import com.example.kidsapp.databinding.FragmentIntroBinding

class IntroFragment : Fragment(R.layout.fragment_intro_) {

    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentIntroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //declare the animation
        val ttb = AnimationUtils.loadAnimation(this.context, R.anim.ttb)

        //set the animation
        val introImage1 = binding.imageIntro.startAnimation(ttb)

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_createProfileFragment)
        }

    }







}

