package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieLongCommentList
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieLongCommentListViewModel : BaseViewModel() {
    var movieId: IntLiveData = IntLiveData()
    val offset = 0
    var limit = 10

    val movieLongCommentList = MutableLiveData<ResultState<MovieLongCommentList>>()


    fun getMovieLongCommentList(movieId: Int, limit: Int, offset: Int) {

        requestNoCheck({ apiService.getMovieLongCommentList(movieId, limit, offset)},movieLongCommentList)

    }
}