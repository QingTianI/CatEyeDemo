package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieVideoCommentBinding
import com.example.jetpackdemo.viewmodel.fragment.MovieVideoCommentViewModel

class MovieVideoCommentFragment :
    BaseFragment<MovieVideoCommentViewModel, FragmentMovieVideoCommentBinding>() {


    override fun createObserver() {


    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_video_comment
    }

    override fun initView(savedInstanceState: Bundle?) {
    }


    companion object {

        fun newInstance() =
            MovieVideoCommentFragment ().apply {
                arguments = Bundle().apply {

                }
            }
    }


}