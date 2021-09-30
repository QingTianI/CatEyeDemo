package com.example.jetpackdemo.viewmodel.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieStarHonor
import com.example.jetpackdemo.data.model.bean.MovieStarInfo
import com.example.jetpackdemo.data.model.bean.StarMovies
import com.example.jetpackdemo.data.model.bean.StarRelatedPeople
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieStarInfoBeanViewModel : BaseViewModel() {
    var movieStarId: IntLiveData = IntLiveData()
    var offset = 0
    val limit = 10

    var tvHeaderTitle = ""
    var tvHeaderSubTitle = ""
    var awardUrl :String? = ""

    val movieStarInfo = MutableLiveData<ResultState<MovieStarInfo>>()

    val starMovies= MutableLiveData<ResultState<StarMovies>>()

    val movieStarHonor = MutableLiveData<ResultState<MovieStarHonor>>()


    val moreStarMovies = MutableLiveData<ResultState<StarMovies>>()

    //相关影人
    val starRelatedPeople = MutableLiveData<ResultState<StarRelatedPeople>>()


    fun getMovieStarInfoBean(movieStarId: Int) {

        requestNoCheck({ apiService.getMovieStarInfoBean(movieStarId) }, movieStarInfo)

    }

    fun getMovieStarHonors(movieStarId: Int) {
        requestNoCheck({ apiService.getMovieStarHonors(movieStarId) }, movieStarHonor)


    }

    fun getStarMovieBean(movieStarId: Int, limit: Int, offset: Int) {

        requestNoCheck({ apiService.getStarMovies(movieStarId, limit, offset) }, starMovies)

    }

    fun getLoadMoreStarMovieBean(movieStarId: Int, limit: Int, offset: Int) {


        requestNoCheck({ apiService.getStarMovies(movieStarId, limit, offset) }, moreStarMovies)

    }

    fun awardTotal(awardCount: String?): String {

        return if (awardCount != null) {
            "获奖${awardCount}次"

        } else {

            ""
        }

    }

    /**
     *
     * @param count Int
     * @return String
     */
    fun totalProduct(count: Int) = "全部${count}部"

    /**
     *
     * @param photos Int
     * @return String
     */
    fun totalPhotos(photos: Int) = "全部${photos}张"

    /**
     *
     * @param bgImg String
     * @return Int
     */
    fun rlBigPhotoVisibility(bgImg: String?) = if (bgImg != null) View.VISIBLE else View.GONE
    fun lvSmallPhotoVisibility(bgImg: String?) = if (bgImg != null) View.GONE else View.VISIBLE

    fun getStarRelatedPeopleBean(movieStarId: Int) {

        requestNoCheck({ apiService.getStarRelatedPeople(movieStarId) }, starRelatedPeople)


    }
}