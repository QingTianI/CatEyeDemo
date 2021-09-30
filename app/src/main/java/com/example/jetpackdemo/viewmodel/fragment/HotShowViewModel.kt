package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.HotMovieList
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class HotShowViewModel : BaseViewModel() {

    var totalPager = 0
    var currentPager = 2
    val mMovieIds = mutableListOf<List<Int>>()
    val hotMovieListBean = MutableLiveData<ResultState<HotMovieList>>()
    val moreMovieListBean = MutableLiveData<ResultState<HotMovieList>>()


    fun getHostList() {

        requestNoCheck({ apiService.getHostList(12) }, hotMovieListBean)


    }

    fun getLoadMoreHotMovieList(headline: Int, movieIds: String) {

        requestNoCheck({ apiService.getMoreHotMovieList(headline, movieIds)},moreMovieListBean)

    }

}

