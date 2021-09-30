package com.example.jetpackdemo.data.model.bean


data class MovieLongCommentList(
    val `data`: Data
){
    data class Data(
        val filmReviews: List<FilmReview>,
        val paging: Paging,
        val timestamp: Long
    )

    data class FilmReview(
        val author: Author,
        val commentCount: Int,
        val created: Long,
        val id:Int,
        val movie: Any,
        val movieId: Int,
        val pro: Boolean,
        val sc: Double ,
        val supportComment: Boolean,
        val supportLike: Boolean,
        val text: String,
        val title: String,
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

    data class Author(
        val age: String,
        val authInfo: String,
        val avatarType: Int,
        val avatarurl: String,
        val birthday: Long,
        val certificateIcon: String,
        val certificateName: String,
        val certificateRedirectUrl: String,
        val city: City,
        val currentExp: Int,
        val gender: Int,
        val id: Long,
        val interest: String,
        val juryLevel: Int,
        val maoyanAge: String,
        val marriage: String,
        val mobile: String,
        val nextTitle: String,
        val nickName: String,
        val nickNameStatus: Int,
        val occupation: String,
        val registerTime: Long,
        val roleInfo: String,
        val roleType: Int,
        val signature: String,
        val signatureStatus: Int,
        val title: String,
        val tmpNickName: String,
        val tmpSignature: String,
        val topicCount: Int,
        val totalExp: Int,
        val uid: Int,
        val userLevel: Int,
        val userNextLevel: Int,
        val username: String,
        val vipInfo: String,
        val vipType: Int,
        val visitorCount: Int
    )

    data class City(
        val id: Int,
        val nm: String,
        val py: String
    )
}

