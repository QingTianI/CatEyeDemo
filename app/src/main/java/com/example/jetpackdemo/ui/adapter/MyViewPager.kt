package com.example.jetpackdemo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyViewPager(list: List<Fragment>, fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {

    private val list = list
    override fun getCount(): Int {

        return list.size

    }

    override fun getItem(position: Int): Fragment {

        return list[position]

    }

}