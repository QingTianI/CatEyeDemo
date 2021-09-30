package com.example.jetpackdemo.viewmodel.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.*
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieDetailViewModel : BaseViewModel() {
    var flag = true
    var prePosition = 0

    lateinit var movieBean: MovieBasicData.Movie

    var movieId = IntLiveData()
    var movieName: String = ""

    val movieBasicData = MutableLiveData<ResultState<MovieBasicData>>()

    val movieStar = MutableLiveData<ResultState<MovieStar>>()
    val movieResource = MutableLiveData<ResultState<MovieResource>>()
    val boxOffice = MutableLiveData<ResultState<BoxOffice>>()
    lateinit var boxOfficeUrl: String

    val movieRelatedNews = MutableLiveData<ResultState<MovieRelatedNews>>()
    val relatedRelatedMovies = MutableLiveData<ResultState<RelatedMovies>>()
    val movieCommentTag = MutableLiveData<ResultState<MovieCommentTag>>()

    val movieLongComment = MutableLiveData<ResultState<MovieLongComment>>()

    fun getMovieBasicData(movieIdObserve: Int) {

        requestNoCheck({ apiService.getMovieBasicData(movieIdObserve) }, movieBasicData)


    }

    fun getMovieStarList(movieIdObserve: Int) {

        requestNoCheck({ apiService.getMovieStarList(movieIdObserve) }, movieStar)


    }

    fun getMovieResource(movieIdObserve: Int) {

        requestNoCheck({ apiService.getMovieResource(movieIdObserve) }, movieResource)

    }

    fun getBoxOffice(movieIdObserve: Int) {

        requestNoCheck({ apiService.getBoxOffice(movieIdObserve) }, boxOffice)

    }

    fun getMovieRelatedNews(movieIdObserve: Int) {

        requestNoCheck(
            { apiService.getMovieRelatedInfo(movieIdObserve) },
            movieRelatedNews
        )

    }

    fun getRelatedMovies(movieIdObserve: Int) {

        requestNoCheck(
            { apiService.getRelatedMovies(movieIdObserve) },
            relatedRelatedMovies
        )
    }

    fun getMoviesComment(movieIdObserve: Int) {
        requestNoCheck(
            { apiService.getMovieCommentTag(movieIdObserve) },
            movieCommentTag
        )

    }

    fun getMovieLongComment(movieId: Int) {

        requestNoCheck({ apiService.getMovieLongComment(movieId) }, movieLongComment)
    }


    fun lvMCVisibility(sc: Double): Int {

        return if (sc != 0.0) {

            View.VISIBLE
        } else {

            View.GONE
        }

    }

    fun lvMWVisibility(sc: Double): Int {
        return if (sc != 0.0) {

            View.GONE
        } else {

            View.VISIBLE
        }


    }

    /**
     * "全部19人"
     */
    fun totalPeopleCount(int: Int) = "全部${int}人"

    /**
     *
     * @param size Int
     * @return String
     */
    fun tvAllCount(size: Int) = "查看全部${size}条长评"

    fun setFirstWeekBox(mbox: BoxOffice.Mbox?): String {

        val firstWeekBox = mbox?.firstWeekBox   //首周票房

        /**
         *
         *    判断电影票房数据
         * @see firstWeekBox= 0     则为当前周上映,没有上周票房记录
         */
        if (firstWeekBox == 0) {


            return "暂无"

        } else {

            val formterFirstWeekBox = StringBuilder().run {

                val right = firstWeekBox?.rem(1000)
                val left = firstWeekBox?.div(1000)
                append(left)
                append(",")
                append(right)
                toString()

            }

            return formterFirstWeekBox
        }


    }

    fun setSumBox(mbox: BoxOffice.Mbox?): String {
        val sumBox = mbox?.sumBox   //总票房

        val formmterSumBox = StringBuilder().run {

            val right = sumBox?.rem(1000)
            val left = sumBox?.div(1000)
            append(left)
            append(",")
            append(right)
            toString()

        }

        return formmterSumBox
    }

}