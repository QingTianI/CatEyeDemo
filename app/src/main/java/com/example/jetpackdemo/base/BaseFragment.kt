package com.example.jetpackdemo.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

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
//    override fun onAttach(context: Context) {
//
//
//        LogUtils.debugInfo(" - onFragmentAttached", this.javaClass.simpleName)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        LogUtils.debugInfo(" - onFragmentCreated", this.javaClass.simpleName)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        LogUtils.debugInfo(" - onFragmentCreatedView", this.javaClass.simpleName)
//
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        LogUtils.debugInfo(" - onFragmentActivityCreated", this.javaClass.simpleName)
//
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        LogUtils.debugInfo(" - onFragmentStarted", this.javaClass.simpleName)
//    }


//
//    override fun onPause() {
//        super.onPause()
//        LogUtils.debugInfo(" - onFragmentPaused", this.javaClass.simpleName)
//
//    }
//
//    override fun onStop() {
//        super.onStop()
//        LogUtils.debugInfo(" - onFragmentStopped", this.javaClass.simpleName)
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        LogUtils.debugInfo(" - onFragmentDestroyView", this.javaClass.simpleName)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        LogUtils.debugInfo(" - onFragmentDestroyed", this.javaClass.simpleName)
//
//
//    }


}