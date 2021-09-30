package com.example.jetpackdemo.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    abstract override fun layoutId(): Int

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *  设置数据
         */
        initData(savedInstanceState)

        /**
         * 设置title
         */
        initTitle()

    }


    abstract fun initTitle()


    abstract override fun createObserver()

    override fun dismissLoading() {


    }

    override fun showLoading(message: String) {


    }


    abstract fun initData(savedInstanceState: Bundle?)


}