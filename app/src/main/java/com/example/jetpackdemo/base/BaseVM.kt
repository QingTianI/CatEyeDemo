package com.example.jetpackdemo.base

import android.os.Bundle
import me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

abstract class BaseVM<VM:BaseViewModel>: BaseVmFragment<VM>(){


    abstract override fun createObserver()


    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {


    }


    override fun lazyLoadData() {


    }
//

}