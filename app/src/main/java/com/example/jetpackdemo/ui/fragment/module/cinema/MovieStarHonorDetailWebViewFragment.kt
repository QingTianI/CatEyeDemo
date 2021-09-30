package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieStarHonorDetailWebViewBinding
import com.example.jetpackdemo.util.loadWebView
import com.example.jetpackdemo.util.setNormalTitle
import com.example.jetpackdemo.viewmodel.fragment.MovieStarHonorDetailWebViewViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class MovieStarHonorDetailWebViewFragment :
    BaseFragment<MovieStarHonorDetailWebViewViewModel, FragmentMovieStarHonorDetailWebViewBinding>() {


    override fun createObserver() {


        /**
         *  监听Bundle参数传输数变化
         *  @see mViewModel.awardUrl
         */
        monitorBundleParamAwardUrl()

    }

    private fun monitorBundleParamAwardUrl() {

        mViewModel.awardUrl.observe(this) {

            if (!it.isEmpty()) {

                mDatabind.webView.loadWebView(it)
            } else {


                it.loge(javaClass.simpleName + ".bundle.params.awardUrl")
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_star_honor_detail_web_view
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         * 初始化title
         */
        initTitle()


        /**
         * 接收Bundle参数
         */
        receiveBundleParam()

    }

    private fun initTitle() {


        mDatabind.normalTitle.toolbar.setNormalTitle(this, "荣誉成就")


    }

    private fun receiveBundleParam() {


        arguments?.let {
            mViewModel.awardUrl.value =
                MovieStarHonorDetailWebViewFragmentArgs.fromBundle(it).awardUrl
        }


    }

}