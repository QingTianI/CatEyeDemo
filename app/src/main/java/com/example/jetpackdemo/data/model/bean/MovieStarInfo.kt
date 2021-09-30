package com.example.jetpackdemo.data.model.bean


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class MovieStarInfo(
    val `data`: Data
)  : Parcelable{

    data class Data(
        val aliasName: String,
        val attachUserId: Int,
        val auth: Int,
        val avatar: String,
        val bgImg: String,
        val birthday: String,
        val birthplace: String,
        val bloodType: String,
        val boardUrl: String,
        val cnm: String,
        val company: String,
        val constellation: String,
        val deathDate: String,
        val desc: String,
        val enm: String,
        val fansName: String,
        val followCount: Int,
        val followRank: Int,
        val followState: Int,
        val graduateSchool: String,
        val height: Int,
        val id: Int,
        val nation: String,
        val nationality: String,
        val photoNum: Int,
        val photos: List<String>,
        val present: Int,
        val presentImg: String,
        val publicTitles: List<Any>,
        val rank: Int,
        val receiveWord: String,
        val sendWord: String,
        val sexy: String,
        val signImg: String,
        val still: String,
        val sumBox: Int,
        val titleList: List<String>,
        val titles: String,
        val userDailyPresent: Int
    ) :Serializable
}

