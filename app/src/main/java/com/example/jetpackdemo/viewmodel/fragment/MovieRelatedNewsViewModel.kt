package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieRelatedNewsViewModel : BaseViewModel() {
    var movieId: IntLiveData = IntLiveData()
    var movieName = StringLiveData()

    var limit = 10
    var offset = 0


    val movieRelatedNews = MutableLiveData<ResultState<MovieRelatedNews>>()

    fun getMovieRelatedNews(movieIdObserve: Int, limit: Int, offset: Int) {

        requestNoCheck({ apiService.getMovieRelatedNews(movieIdObserve,limit, offset)},movieRelatedNews)
    }

}