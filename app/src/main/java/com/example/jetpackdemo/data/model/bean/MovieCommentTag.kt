package com.example.jetpackdemo.data.model.bean


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MovieCommentTag(
    val `data`: List<Data>
) {
    @Parcelize
    data class Data(
        val count: Int,
        val movieId: Int,
        val position: Int,
        val recommend: Boolean,
        val tag: Int,
        val tagName: String,
        val type: Int,
        var isSelected:Boolean
    ):Parcelable
}

