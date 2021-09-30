package com.example.jetpackdemo.ui.fragment.module.show

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentShowBinding
import com.example.jetpackdemo.viewmodel.fragment.ShowViewModel

class ShowFragment : BaseFragment<ShowViewModel,FragmentShowBinding>() {
    override fun createObserver() {


    }

    override fun layoutId(): Int {
        return R.layout.fragment_show
    }

    override fun initView(savedInstanceState: Bundle?) {
    }


}