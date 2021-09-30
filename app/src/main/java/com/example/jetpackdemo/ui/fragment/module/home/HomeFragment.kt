package com.example.jetpackdemo.ui.fragment.module.home

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentHomeBinding
import com.example.jetpackdemo.viewmodel.fragment.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding>() {


    override fun createObserver() {



    }

    override fun layoutId(): Int {

        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}