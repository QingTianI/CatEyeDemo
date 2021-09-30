package com.example.jetpackdemo.data.model.bean


data class BoxOffice(
    val globalRelease: Boolean,
    val mbox: Mbox,
    val url: String
) {
    data class Mbox(
        val firstWeekBox: Int,
        val firstWeekOverSeaBox: Int,
        val lastDayRank: Int,
        val sumBox: Int,
        val sumOverSeaBox: Int
    )

}


