package com.example.kidsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.NumbersAdapter
import com.example.kidsapp.databinding.FragmentSingleNumberBinding

class SingleNumberFragment : Fragment() {

    private var _binding: FragmentSingleNumberBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_POSITION = "position"
        private val numbers = listOf(
            Pair("1", "one"),
            Pair("2", "two"),
            Pair("3", "three"),
            Pair("4", "four"),
            Pair("5", "five"),
            Pair("6", "six"),
            Pair("7", "seven"),
            Pair("8", "eight"),
            Pair("9", "nine"),
            Pair("0", "zero")
        )

        fun newInstance(position: Int): SingleNumberFragment {
            val fragment = SingleNumberFragment()
            val bundle = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_POSITION) ?: 0
        updateUI(position)

        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val adapter = NumbersAdapter(numbers) { selectedNumber ->
            updateUIForNumber(selectedNumber)
        }

        binding.rvNumbers.adapter = adapter
        binding.rvNumbers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun updateUI(position: Int) {
        val numberText = numbers[position].first
        val numberWord = numbers[position].second

        binding.tvNumber.text = numberText
        binding.tvNumberText.text = "$numberText = $numberWord"
    }

    private fun updateUIForNumber(number: Int) {

        binding.tvNumber.text = number.toString()
        binding.tvNumberText.text = "$number = ${numberToText(number)}"
    }

    private fun numberToText(number: Int): String {
        return when (number) {
            0 -> "zero"
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            5 -> "five"
            6 -> "six"
            7 -> "seven"
            8 -> "eight"
            9 -> "nine"
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
