package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieCommentTag
import com.example.jetpackdemo.data.model.bean.MovieCommentTagDetailed
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieShortCommentViewModel : BaseViewModel() {
    var prePosition = 0
    var offset = 0
    var limit = 10


    var movieId: IntLiveData = IntLiveData()
    var movieName: StringLiveData = StringLiveData()
    var tag: IntLiveData = IntLiveData()
    var monitorMovieIdBoolean = false
    var monitorTagBoolean = false

    val movieCommentTagDetailed =
        MutableLiveData<ResultState<MovieCommentTagDetailed>>()

    val moreMovieCommentTagDetailed =
        MutableLiveData<ResultState<MovieCommentTagDetailed>>()

    val  movieCommentTag_Data_List  = MutableLiveData<List<MovieCommentTag.Data>>()





    fun getMovieCommentShortDetailed(
        movieId: Int,
        tag: Int,
        limit: Int,
        offset: Int
    ) {

        requestNoCheck({
            apiService.getMovieCommentTagDetailed(
                movieId,
                tag,
                limit,
                offset
            )
        }, movieCommentTagDetailed)


    }

    fun getMoreMovieCommentShortDetailed(movieId: Int, tag: Int, limit: Int, offset: Int) {


        requestNoCheck({
            apiService.getMovieCommentTagDetailed(
                movieId,
                tag,
                limit,
                offset
            )
        }, moreMovieCommentTagDetailed)



    }
}