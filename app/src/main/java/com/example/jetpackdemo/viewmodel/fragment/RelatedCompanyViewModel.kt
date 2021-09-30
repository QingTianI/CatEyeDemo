package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieRelatedCompanies
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class RelatedCompanyViewModel : BaseViewModel() {

    var movieId: IntLiveData = IntLiveData()

    val movieRelatedCompanies = MutableLiveData<ResultState<MovieRelatedCompanies>>()



    fun getMovieRelatedCompanies(movieIdObserve: Int) {
        requestNoCheck({ apiService. getMovieRelatedCompanies(movieIdObserve)},movieRelatedCompanies)

    }

}