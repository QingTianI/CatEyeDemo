package com.example.jetpackdemo.data.model.bean


data class MovieCommentTagDetailed(
    val `data`: Data,
    val paging: Paging
) {
    data class Data(
        val cmts: List<Cmt>,
        val hcmts: List<Any>,
        val hotIds: List<Int>
    )

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

    data class Cmt(
        val approve: Int,
        val approved: Boolean,
        val assistAwardInfo: AssistAwardInfo,
        val authInfo: String,
        val avatarurl: String,
        val cityName: String,
        val content: String,
        val created: Long,
        val filmView: Boolean,
        val follow: Boolean,
        val gender: Int,
        val id: Int,
        val isMajor: Boolean,
        val juryLevel: Int,
        val movieId: Int,
        val nick: String,
        val nickName: String,
        val oppose: Int,
        val pro: Boolean,
        val reply: Int,
        val score: Double,
        val spoiler: Int,
        val startTime: String,
        val supportComment: Boolean,
        val supportLike: Boolean,
        val sureViewed: Int,
        val tagList: TagList,
        val time: String,
        val topicSelf: List<Any>,
        val userId: Long,
        val userLevel: Int,
        val vipInfo: String,
        val vipType: Int
    )

    data class AssistAwardInfo(
        val avatar: String,
        val celebrityId: Int,
        val celebrityName: String,
        val rank: Int,
        val title: String
    )

    data class TagList(
        val fixed: List<Fixed>
    )

    data class Fixed(
        val id: Int,
        val name: String
    )
}
