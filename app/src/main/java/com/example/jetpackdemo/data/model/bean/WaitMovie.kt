package com.example.jetpackdemo.data.model.bean


data class WaitMovie(
    val data: Data
) {

    data class Data(
        val coming: List<Coming>,
        val hot: List<Any>,
        val movieIds: List<Int>,
        val stid: String
    )

    data class Coming(
        val bingeWatch: Int,
        val boxInfo: String,
        val cat: String,
        val civilPubSt: Int,
        val comingTitle: String,
        val desc: String,
        val dir: String,
        val dur: Int,
        val effectShowNum: Int,
        val followst: Int,
        val fra: String,
        val frt: String,
        val ftime: String,
        val globalReleased: Boolean,
        val haspromotionTag: Boolean,
        val headLineShow: Boolean,
        val headLinesVO: List<Any>,
        val id: Int,
        val img: String,
        val isRevival: Boolean,
        val late: Boolean,
        val localPubSt: Int,
        val mark: Boolean,
        val mk: Int,
        val movieType: Int,
        val nm: String,
        val pn: Int,
        val preShow: Boolean,
        val proScore: Int,
        val proScoreNum: Int,
        val pubDate: Long,
        val pubDesc: String,
        val pubShowNum: Int,
        val recentShowDate: Int,
        val recentShowNum: Int,
        val rt: String,
        val sc: Int,
        val scm: String,
        val scoreLabel: String,
        val showCinemaNum: Int,
        val showInfo: String,
        val showNum: Int,
        val showst: Int,
        val snum: Int,
        val star: String,
        val ver: String,
        val videoId: Int,
        val videoName: String,
        val videourl: String,
        val vnum: Int,
        val vodPlay: Boolean,
        val weight: Int,
        val wish: Int,
        val wishst: Int,
        val trailerDataBeanList:MutableList<PreviewedRecommend.Data>,
        val recentExpectList:MutableList<ExpectMovie.Coming>

    )





}

