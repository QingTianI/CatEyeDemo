package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentWebViewNoTitleBinding
import com.example.jetpackdemo.util.loadWebView
import com.example.jetpackdemo.viewmodel.fragment.WebViewNoTitleViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class WebViewNoTitleFragment :
    BaseFragment<WebViewNoTitleViewModel, FragmentWebViewNoTitleBinding>() {


    override fun createObserver() {
        /**
         * 监听Bundle传输数据变化
         * @see mViewModel.webViewUrl
         */
        monitorBundleParamWebViewUrl()

    }

    private fun monitorBundleParamWebViewUrl() {
        mViewModel.webViewUrl.observe(this) {

            if (!it.isEmpty()) {

                mDatabind.webView.loadWebView(it)
            } else {

                it.loge(javaClass.simpleName + ".bundle.params.webViewUrl")


            }

        }


    }

    override fun layoutId(): Int {
        return R.layout.fragment_web_view_no_title
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         * 接收Bundle参数
         */
        receiverBundleParam()
    }

    private fun receiverBundleParam() {


        arguments?.let {
            mViewModel.webViewUrl.value = WebViewNoTitleFragmentArgs.fromBundle(it).webViewUrl

        }
    }


}