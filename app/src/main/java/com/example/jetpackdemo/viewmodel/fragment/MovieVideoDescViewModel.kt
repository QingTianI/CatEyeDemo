package com.example.jetpackdemo.viewmodel.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieBasicData
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieVideoDescViewModel : BaseViewModel() {

    val limit = 20
    var prePosition = 0
    var movieId: Int? = 0
    val videoPreview_Data = MutableLiveData<VideoPreview.Data>()
    val videoPreview = MutableLiveData<ResultState<VideoPreview>>()

    val movieBasicData = MutableLiveData<ResultState<MovieBasicData>>()


    fun getMovieBasicData(movieId: Int) {

        requestNoCheck({ apiService.getMovieBasicData(movieId) }, movieBasicData)

    }


    /**
     *
     * @param globalReleased Boolean
     * @return Int
     */
    fun lvHMVisibility(globalReleased: Boolean): Int {


        return if (globalReleased) {

            View.VISIBLE
        } else {

            View.GONE
        }
    }

    fun lvPMVisibility(globalReleased: Boolean): Int {


        return if (globalReleased) {

            View.GONE
        } else {

            View.VISIBLE
        }
    }


    fun getPreviewVideoList(movieId: Int, limit: Int, offset: Int) {

        requestNoCheck(
            { apiService.getPreviewVideoList(movieId, limit, offset) },
            videoPreview
        )

    }
}