package com.example.jetpackdemo.viewmodel.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieVideoViewModel : BaseViewModel() {
    val limit = 10
    val offset = 0

    val arrayList = ArrayList<Fragment>()
    var movieId: IntLiveData = IntLiveData()

    val videoPreview_Data = MutableLiveData<VideoPreview.Data>()
    val videoPreview = MutableLiveData<ResultState<VideoPreview>>()



    fun getPreviewVideoList(movieId: Int, limit: Int, offset: Int) {

        requestNoCheck(
            { apiService.getPreviewVideoList(movieId, limit, offset) },
            videoPreview
        )

    }

}