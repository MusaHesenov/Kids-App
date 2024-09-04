package com.example.kidsapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kidsapp.fragments.SingleNumberFragment

class NumberPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        return SingleNumberFragment.newInstance(position)
    }
}