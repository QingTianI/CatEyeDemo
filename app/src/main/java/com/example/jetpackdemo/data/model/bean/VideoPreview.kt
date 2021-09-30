package com.example.jetpackdemo.data.model.bean


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class VideoPreview(
    val `data`: List<Data>,
    val paging: Paging
) {
    @Parcelize
    data class Data(
        val approve: Int,
        val boardContent: String?,
        val boardSchema: String?,
        val comment: Int,
        val count: Int,
        val createTime: String,
        val detailUrl: String,
        val id: Int,
        val img: String,
        val isApprove: Boolean,
        val movieId: Int,
        val movieName: String,
        val pubTime: String,
        val shareInfo: ShareInfo,
        val showSt: Int,
        val tl: String,
        val tm: Int,
        val type: Int,
        val url: String,
        val videoSize: Int,
        var isSelect: Boolean
    ) : Parcelable

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

    data class ShareInfo(
        val channel: Int,
        val content: String,
        val img: String,
        val title: String,
        val url: String
    ) : Serializable
}

