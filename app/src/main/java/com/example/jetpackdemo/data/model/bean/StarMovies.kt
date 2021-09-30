package com.example.jetpackdemo.data.model.bean


data class StarMovies(
    val `data`: Data
) {

    data class Data(
        val movies: List<Movy>,
        val paging: Paging,
        val showAvatarDetail: Boolean
    )

    data class Movy(
        val avatar: String,
        val cat: String,
        val cr: Int,
        val desc: String,
        val globalReleased: Boolean,
        val id: Int,
        val img: String,
        val mbox: Int,
        val multiroles: String,
        val mutlidutys: String,
        val name: String,
        val order: Int,
        val pubDate: Long,
        val pubDesc: String,
        val rt: String,
        val sc: Double,
        val showst: Int,
        val still: String,
        val title: String,
        val wish: Int,
        val wishst: Int,
        val worksType: Int,
        val worksTypeDesc: String
    )

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

}
