package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieStarPhotoDetailsBinding
import com.example.jetpackdemo.viewmodel.fragment.MovieStarPhotoDetailsViewModel
import me.hgj.jetpackmvvm.ext.util.loge

class MovieStarPhotoDetailsFragment :
    BaseFragment<MovieStarPhotoDetailsViewModel, FragmentMovieStarPhotoDetailsBinding>() {


    override fun createObserver() {

        /**
         * 监听Bundle数据变化
         * @see mViewModel.movieName
         */
        monitorBundleParamMovieName()


        /**
         * 监听Bundle数据变化
         * @see mViewModel.photos
         */
        monitorBundleParamPhotos()

    }

    private fun monitorBundleParamPhotos() {

        mViewModel.photos.observe(this){

            if (!it.isEmpty()){

            }else{

                it.toString().loge(javaClass.simpleName + ".bundle.params.photos")

            }

        }
    }

    private fun monitorBundleParamMovieName() {

        mViewModel.movieName.observe(this) {

            if (!it.isEmpty()) {

            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieName")


            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_star_photo_details
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *  接收Bundle参数
         *
         */
        receiverBundleParam()
    }

    private fun receiverBundleParam() {

        arguments?.let {
            mViewModel.movieName.value =  MovieStarPhotoDetailsFragmentArgs.fromBundle(it).movieName
            mViewModel.photos.value =  MovieStarPhotoDetailsFragmentArgs.fromBundle(it).photos.toList()
        }

    }


}