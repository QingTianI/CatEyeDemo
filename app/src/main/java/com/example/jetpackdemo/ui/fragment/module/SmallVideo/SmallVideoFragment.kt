package com.example.jetpackdemo.ui.fragment.module.SmallVideo

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentSmallVideoBinding
import com.example.jetpackdemo.viewmodel.fragment.SmallVideoViewModel

class SmallVideoFragment : BaseFragment<SmallVideoViewModel,FragmentSmallVideoBinding>() {
    override fun createObserver() {
    }

    override fun layoutId(): Int {

        return R.layout.fragment_small_video
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

}