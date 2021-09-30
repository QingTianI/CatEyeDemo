package com.example.jetpackdemo.ui.fragment.module

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMainBinding
import com.example.jetpackdemo.util.init
import com.example.jetpackdemo.util.initMain
import com.example.jetpackdemo.viewmodel.fragment.MainViewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {



    override fun createObserver() {


    }

    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(savedInstanceState: Bundle?) {

        //初始化viewpager2
        mDatabind.mainViewpager.initMain(this)


        //初始化 bottomBar
        mDatabind.mainBottom.init {
            when (it) {
                R.id.menu_home -> mDatabind.mainViewpager.setCurrentItem(0, false)
                R.id.menu_cinema -> mDatabind.mainViewpager.setCurrentItem(1, false)
                R.id.menu_small_video -> mDatabind.mainViewpager.setCurrentItem(2, false)
                R.id.menu_show -> mDatabind.mainViewpager.setCurrentItem(3, false)
                R.id.menu_mine -> mDatabind.mainViewpager.setCurrentItem(4, false)
            }
        }

        /**
         *  @see  defaultPosition
         */
        mDatabind.mainViewpager.currentItem = 1
        mDatabind.mainBottom.currentItem = 1


    }

}