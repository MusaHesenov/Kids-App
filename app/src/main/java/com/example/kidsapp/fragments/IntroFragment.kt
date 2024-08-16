package com.example.kidsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.kidsapp.R
import com.example.kidsapp.activities.HomeActivity
import com.example.kidsapp.activities.MainActivity
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
        binding.imageIntro.startAnimation(ttb)

        val viewModel = (activity as MainActivity).viewModel

        val isUserSignedIn = viewModel.isUserSignedIn()
        println("isUserSignedIn: $isUserSignedIn")
        if (isUserSignedIn) {
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            Handler().postDelayed({
                startActivity(intent)
            }, 1500)
        } else
            Handler().postDelayed({
                findNavController().navigate(R.id.action_introFragment_to_loginFragment)
            }, 1500)

    }







}

