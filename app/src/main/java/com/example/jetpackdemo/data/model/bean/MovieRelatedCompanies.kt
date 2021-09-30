package com.example.jetpackdemo.data.model.bean


import java.io.Serializable

data class MovieRelatedCompanies(

    val `data`: List<Data>) :Serializable {
    data class Data(
        val cmpTypeId: Int,
        val cmpTypeName: String,
        val items: List<Item>):Serializable

    data class Item(
        val desc: String,
        val id: Int):Serializable
}

