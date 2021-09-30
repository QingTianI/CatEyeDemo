package com.example.jetpackdemo.data.model.bean


data class StarRelatedPeople(
    val `data`: Data
) {
    data class Data(
        val relations: List<Relation>
    )

    data class Relation(
        val avatar: String,
        val id: Int,
        val name: String,
        val relation: String
    )
}

