package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieTechnicals
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieTechnicalsViewModel : BaseViewModel() {
    var movieId = IntLiveData()
    val movieTechnicals = MutableLiveData<ResultState<MovieTechnicals>>()

    fun getMovieTechnicals(movieIdObserve: Int) {

        requestNoCheck({ apiService.getMovieTechnicals(movieIdObserve)},movieTechnicals)

    }

}