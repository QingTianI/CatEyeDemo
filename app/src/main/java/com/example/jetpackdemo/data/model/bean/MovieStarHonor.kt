package com.example.jetpackdemo.data.model.bean


data class MovieStarHonor(
    val `data`: Data){

data class Data(
    val award: String,
    val awardCount: String,
    val awardDesc: String,
    val awardTotal: Int,
    val awardUrl: String,
    val festivalName: String,
    val nomCount: String,
    val prizeDesc: String,
    val prizeImg: String
)}
