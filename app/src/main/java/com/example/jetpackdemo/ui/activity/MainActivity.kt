package com.example.jetpackdemo.ui.activity

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseActivity
import com.example.jetpackdemo.databinding.ActivityMainBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel,ActivityMainBinding>() {


    override fun layoutId(): Int {

        return R.layout.activity_main

    }

    override fun initTitle() {
    }

    override fun createObserver() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
}