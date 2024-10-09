package com.example.kidsapp.fragments

import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsapp.R
import com.example.kidsapp.adapters.AlphabetAdapter
import com.example.kidsapp.databinding.FragmentSingleAlphabetBinding


class SingleAlphabetFragment : Fragment() {

    private var _binding : FragmentSingleAlphabetBinding? = null
    private val binding get() = _binding!!

    companion object{
        private const val ARG_POSITION = "position"
        private val alphabet = listOf(
            Pair("A", "1st letter"),
            Pair("B", "2nd letter"),
            Pair("C", "3rd letter"),
            Pair("D", "4th letter"),
            Pair("E", "5th letter"),
            Pair("F", "6th letter"),
            Pair("G", "7th letter"),
            Pair("H", "8th letter"),
            Pair("I", "9th letter"),
            Pair("J", "10th letter"),
            Pair("K", "11th letter"),
            Pair("L", "12th letter"),
            Pair("M", "13th letter"),
            Pair("N", "14th letter"),
            Pair("O", "15th letter"),
            Pair("P", "16th letter"),
            Pair("Q", "17th letter"),
            Pair("R", "18th letter"),
            Pair("S", "19th letter"),
            Pair("T", "20th letter"),
            Pair("U", "21st letter"),
            Pair("V", "22nd letter"),
            Pair("W", "23rd letter"),
            Pair("X", "24th letter"),
            Pair("Y", "25th letter"),
            Pair("Z", "26th letter")
        )

        fun newInstance(position: Int): SingleAlphabetFragment{
            val fragment = SingleAlphabetFragment()
            val bundle = Bundle().apply {
                putInt(ARG_POSITION,position)
            }
            fragment.arguments=bundle
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleAlphabetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_POSITION) ?: 0
        updateUI(position)

        val alphabet = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        val adapter = AlphabetAdapter(alphabet) { selectedAlphabet ->
            // Seçilən hərf üçün UI-ni yeniləyin
            updateUIForAlphabet(selectedAlphabet)
        }

        binding.rvAlphabet.adapter = adapter
        binding.rvAlphabet.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }


    private fun updateUI(position: Int) {
        val alphabetText = alphabet[position].first
        val alphabetWord = alphabet[position].second

        binding.tvAlphabet.text = alphabetText
        binding.tvAlphabet.text = "$alphabetText = $alphabetWord"
    }


    private fun updateUIForAlphabet(alphabet: String) {
        val alphabetWord = when (alphabet) {
            "A" -> "1st letter"
            "B" -> "2nd letter"
            "C" -> "3rd letter"
            "D" -> "4th letter"
            "E" -> "5th letter"
            "F" -> "6th letter"
            "G" -> "7th letter"
            "H" -> "8th letter"
            "I" -> "9th letter"
            "J" -> "10th letter"
            "K" -> "11th letter"
            "L" -> "12th letter"
            "M" -> "13th letter"
            "N" -> "14th letter"
            "O" -> "15th letter"
            "P" -> "16th letter"
            "Q" -> "17th letter"
            "R" -> "18th letter"
            "S" -> "19th letter"
            "T" -> "20th letter"
            "U" -> "21st letter"
            "V" -> "22nd letter"
            "W" -> "23rd letter"
            "X" -> "24th letter"
            "Y" -> "25th letter"
            "Z" -> "26th letter"
            else -> ""
        }

        // UI yenilənməsi burada baş verir
        binding.tvAlphabet.text = "$alphabet = $alphabetWord"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}