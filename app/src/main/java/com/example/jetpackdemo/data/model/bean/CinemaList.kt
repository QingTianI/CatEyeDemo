package com.example.jetpackdemo.data.model.bean


data class CinemaList(
    val code: Int,
    val `data`: Data,
    val errMsg: String,
    val success: Boolean
) {

    data class Data(
        val cinemas: List<Cinema>,
        val ct_pois: List<CtPoi>,
        val paging: Paging
    )

    data class Cinema(
        val addr: String,
        val cellShows: List<Any>,
        val cityId: Int,
        val dis: Int,
        val distance: String,
        val follow: Int,
        val id: Int,
        val labels: List<Label>,
        val lat: Double,
        val lng: Double,
        val mark: Int,
        val nm: String,
        val poiId: Int,
        val price: Double,
        val promotion: Promotion,
        val referencePrice: String,
        val score: Double,
        val sellPrice: String,
        val shopId: Int,
        val showTimes: String,
        val tag: Tag
    )

    data class CtPoi(
        val ct_poi: String,
        val poiid: Int
    )

    data class Paging(
        val hasMore: Boolean,
        val limit: Int,
        val offset: Int,
        val total: Int
    )

    data class Label(
        val color: String,
        val name: String,
        val url: String
    )

    data class Promotion(
        val cardPromotionTag: String,
        val couponPromotionTag: String,
        val merchantActivityTag: String,
        val packShowActivityTag: String,
        val platformActivityTag: String,
        val starActivityTag: String
    )

    data class Tag(
        val allowRefund: Int,
        val buyout: Int,
        val cityCardTag: Int,
        val deal: Int,
        val endorse: Int,
        val giftTag: String,
        val hallType: List<String>,
        val hallTypeVOList: List<HallTypeVO>,
        val sell: Int,
        val snack: Int,
        val vipTag: String
    )

    data class HallTypeVO(
        val name: String,
        val url: String
    )

}
