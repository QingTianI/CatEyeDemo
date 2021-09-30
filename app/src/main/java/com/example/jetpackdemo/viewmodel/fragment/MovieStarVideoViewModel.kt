package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieStarVideoViewModel : BaseViewModel() {

    val previewMovieVideoList = mutableListOf<VideoPreview.Data>()
    val officialMovieVideoList = mutableListOf<VideoPreview.Data>()


    val videoPreview = MutableLiveData<ResultState<VideoPreview>>()
    val movieId = IntLiveData()
    val limit = 30

    fun getPreviewVideoList(movieId: Int, limit: Int, offset: Int) {

        requestNoCheck({ apiService.getPreviewVideoList(movieId, limit, offset) }, videoPreview)
    }

}