package com.example.jetpackdemo.data.model.bean


data class MovieResource(
    val `data`: List<Data>
){
    data class Data(
        val content: String,
        val img: String,
        val name: String,
        val title: String,
        var movieId :Int)
}


