package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieResourceMusicBinding
import com.example.jetpackdemo.viewmodel.fragment.MovieResourceMusicViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class MovieResourceMusicFragment :
    BaseFragment<MovieResourceMusicViewModel, FragmentMovieResourceMusicBinding>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_resource_music, container, false)
    }

    override fun createObserver() {

        /**
         * 监听Bundle传输数变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


    }

    private fun monitorBundleParamMovieId() {
        mViewModel.movieId.observe(this) {

            if (it != 0) {


            } else {

               it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")

            }


        }

    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_resource_music
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         * 初始化title
         */
        initTitle()

        /**
         * 接收Bundle参数
         */

        receiverBundleParam()
    }

    private fun receiverBundleParam() {

        arguments?.let {


            mViewModel.movieId.value = MovieResourceMusicFragmentArgs.fromBundle(it).movieId
        }

    }

    private fun initTitle() {


    }

}