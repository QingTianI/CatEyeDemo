package com.example.jetpackdemo.data.model.bean



data class HotMovieList(
    val `data`: Data
) {
    data class Data(
        val chiefBonus: ChiefBonus,
        val coming: List<Any>,
        val hot: List<Hot>,
        val movieIds: List<Int>,
        val stid: String,
        val stids: List<Stid>,
        val total: Int,
        val movies: List<Hot>

    )

    class ChiefBonus

    data class Hot(


        val bingeWatch: Int,
        val boxInfo: String,
        val cat: String,
        val civilPubSt: Int,
        val desc: String,
        val dir: String,
        val dur: Int,
        val effectShowNum: Int,
        val followst: Int,
        val fra: String,
        val frt: String,
        val globalReleased: Boolean,
        val haspromotionTag: Boolean,
        val headLineShow: Boolean,
        val headLines: List<Any>,
        val headLinesVO: List<Any>,
        val id: Int,
        val img: String,
        val isRevival: Boolean,
        val late: Boolean,
        val localPubSt: Int,
        val mark: Boolean,
        val mk: Double,
        val movieType: Int,
        val newsHeadlines: List<Any>,
        val nm: String,
        val pn: Int,
        val preSale: Int,
        val preShow: Boolean,
        val proScore: Int,
        val proScoreNum: Int,
        val pubDate: Long,
        val pubShowNum: Int,
        val recentShowDate: Long,
        val recentShowNum: Int,
        val rt: String,
        val sc: Double,
        val scm: String,
        val scoreLabel: String,
        val showCinemaNum: Int,
        val showInfo: String,
        val showNum: Int,
        val showTimeInfo: String,
        val showst: Int,
        val snum: Int,
        val star: String,
        val totalShowNum: Int,
        val ver: String,
        val videoId: Int,
        val videoName: String,
        val videourl: String,
        val vnum: Int,
        val vodPlay: Boolean,
        val weight: Int,
        val wish: Int,
        val wishst: Int
    )

    data class Stid(
        val movieId: Int,
        val stid: String
    )
}


