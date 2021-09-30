package com.example.jetpackdemo.data.model.bean


data class PickCity(
    val cts: List<Ct>
) {
    data class Ct(
        val id: Int,
        val nm: String,
        val py: String)
}


