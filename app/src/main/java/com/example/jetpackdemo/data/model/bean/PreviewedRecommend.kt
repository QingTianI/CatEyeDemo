package com.example.jetpackdemo.data.model.bean


data class PreviewedRecommend(
    val `data`: List<Data>
) {
    data class Data(
        val img: String,
        val movieId: Int,
        val movieName: String,
        val name: String,
        val originName: String,
        val url: String,
        val videoId: Int,
        val wish: Int
    )
}

