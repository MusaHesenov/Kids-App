package com.example.kidsapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kidsapp.fragments.SingleAlphabetFragment

class AlphabetPagerAdapter(fragmnet : Fragment) : FragmentStateAdapter(fragmnet) {
    override fun getItemCount(): Int  = 26

    override fun createFragment(position: Int): Fragment {
        return SingleAlphabetFragment.newInstance(position)
    }
}