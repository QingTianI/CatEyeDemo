package com.example.jetpackdemo.ui.fragment.module.mine

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMineBinding
import com.example.jetpackdemo.viewmodel.fragment.MineViewModel

class MineFragment : BaseFragment<MineViewModel,FragmentMineBinding>(){
    override fun createObserver() {
    }

    override fun layoutId(): Int {

        return R.layout.fragment_mine
    }

    override fun initView(savedInstanceState: Bundle?) {
    }


}