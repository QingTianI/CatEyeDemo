package com.example.jetpackdemo.data.model.bean


import java.io.Serializable

data class MovieTechnicals(
    val `data`: Data
):Serializable {
    data class Data(
        val items: List<Item>,
        val title: String
    ):Serializable

    data class Item(
        val desc: String,
        val title: String
    ):Serializable
}

