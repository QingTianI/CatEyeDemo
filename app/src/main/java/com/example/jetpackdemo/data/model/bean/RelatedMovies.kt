package com.example.jetpackdemo.data.model.bean


data class RelatedMovies(
    val `data`: List<Data>
){
    data class Data(
        val items: List<Item>,
        val title: String
    )

    data class Item(
        val desc: String,
        val globalReleased: Boolean,
        val img: String,
        val onlinePlay: Boolean,
        val sc: Double,
        val title: String,
        val type: String,
        val wishNum: Int
    )
}

