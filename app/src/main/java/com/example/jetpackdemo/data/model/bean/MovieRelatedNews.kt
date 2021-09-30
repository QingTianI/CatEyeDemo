package com.example.jetpackdemo.data.model.bean


data class MovieRelatedNews(
    val `data`: Data
){
    data class Data(
        val newsList: List<News>,
        val paging: Paging,
        val timestamp: Long
    )

    data class News(
        val commentCount: Int,
        val created: Long,
        val id: Int,
        val imageCount: Int,
        val previewImages: List<PreviewImage>,
        val source: String,
        val tags: List<Any>,
        val text: String,
        val title: String,
        val type: Int,
        val upCount: Int,
        val url: String,
        val viewCount: Int
    )

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

    data class PreviewImage(
        val authorId: Int,
        val height: Int,
        val id: Int,
        val sizeType: Int,
        val targetId: Int,
        val targetType: Int,
        val url: String,
        val width: Int
    )
}

