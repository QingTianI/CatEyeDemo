package com.example.jetpackdemo.data.model.bean


data class ExpectMovie(
    val `data`: Data
) {

    data class Data(
        val chiefBonus: ChiefBonus,
        val coming: List<Coming>,
        val hot: List<Any>,
        val paging: Paging,
        val recommendAds: List<RecommendAd>,
        val stid: String
    )

    class ChiefBonus

    data class Coming(
        val bingeWatch: Int,
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
        val mk: Int,
        val movieType: Int,
        val newsHeadlines: List<Any>,
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
        val wishst: Int



    )

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

    data class RecommendAd(
        val adId: Int,
        val frame: Int,
        val materialId: Int,
        val movieId: Int,
        val positionId: Int
    )
}
