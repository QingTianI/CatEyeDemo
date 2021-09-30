package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieStarDetailBinding
import com.example.jetpackdemo.viewmodel.fragment.MovieStarDetailViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class MovieStarDetailFragment :
    BaseFragment<MovieStarDetailViewModel, FragmentMovieStarDetailBinding>() {


    override fun createObserver() {

        /**
         *  监听Bundle接收数据变化
         *  @see mViewModel.movieStarInfo
         */

        monitorBundleParamMovieStarInfo()
    }

    private fun monitorBundleParamMovieStarInfo() {

        mViewModel.movieStarInfo.observe(this) {

            if (it != null) {


                mDatabind.data = it.data

            } else {

            it.toString().loge(javaClass.simpleName + ".bundle.params.movieStarInfo")


            }

        }


    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_star_detail
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         *  接收Bundle参数
         */
        receiverBundleParam()


    }

    private fun receiverBundleParam() {


        arguments?.let {
            mViewModel.movieStarInfo.value =
                MovieStarDetailFragmentArgs.fromBundle(it).movieStarInfo
        }

    }


}