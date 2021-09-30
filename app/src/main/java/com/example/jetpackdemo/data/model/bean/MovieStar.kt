package com.example.jetpackdemo.data.model.bean


data class MovieStar(
    val `data`: List<List<Data>>,
    val total: Int
) {

    data class Data(

        val avatar: String,
        val cnm: String,
        val cr: Int,
        val desc: String,
        val id: Int,
        val ocr: Int,
        val roles: String,
        val still: String
    )
}