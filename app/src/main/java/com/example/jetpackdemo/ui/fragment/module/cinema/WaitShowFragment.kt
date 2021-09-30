package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentWaitShowBinding
import com.example.jetpackdemo.viewmodel.fragment.WaitShowViewModel

class WaitShowFragment : BaseFragment<WaitShowViewModel,FragmentWaitShowBinding>() {
    override fun createObserver() {
    }

    override fun layoutId(): Int {
        return R.layout.fragment_wait_show
    }

    override fun initView(savedInstanceState: Bundle?) {
    }


}