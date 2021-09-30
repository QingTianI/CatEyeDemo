package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentWebViewBinding
import com.example.jetpackdemo.util.loadWebView
import com.example.jetpackdemo.util.setNormalTitle
import com.example.jetpackdemo.viewmodel.fragment.WebViewViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class WebViewFragment : BaseFragment<WebViewViewModel, FragmentWebViewBinding>() {


    override fun createObserver() {


        /**
         *  监听Bundle传输数据变化
         *
         *  @see mViewModel.webViewUrl
         */
        monitorBundleParamWebViewUrl()
        /**
         *  监听Intent传输数据变化
         *
         *  @see mViewModel.movieName
         */
        monitorBundleParamMovieName()


    }

    private fun monitorBundleParamMovieName() {


        mViewModel.movieName.observe(this){

            if (!it.isEmpty()) {

                /**
                 *  初始化title
                 */
                initTitle(it)

            } else {

                   it.loge(javaClass.simpleName + ".bundle.params.movieName")
            }

        }


    }

    private fun initTitle(movieName: String) {

        mDatabind.normalTitle.toolbar.setNormalTitle(this, movieName)


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
        return R.layout.fragment_web_view
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         *  接收Bundle参数
         */
        receiverBundleParam()
    }

    private fun receiverBundleParam() {


        arguments?.let {

            mViewModel.movieName.value = WebViewFragmentArgs.fromBundle(it).movieName
            mViewModel.webViewUrl.value = WebViewFragmentArgs.fromBundle(it).webViewUrl

        }

    }

}