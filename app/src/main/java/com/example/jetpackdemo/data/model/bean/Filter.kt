package com.example.jetpackdemo.data.model.bean


data class Filter(
    val code: Int,
    val `data`: Data,
    val errMsg: String,
    val success: Boolean
) {
    data class Data(
        val brand: Brand,
        val district: District,
        val hallType: HallType,
        val service: Service,
        val showType: Any,
        val subway: Subway,
        val timeRanges: TimeRanges
    )

    data class Brand(
        val name: String,
        val subItems: List<SubItem>
    )

    data class District(
        val name: String,
        val subItems: List<SubItemX>
    )

    data class HallType(
        val name: String,
        val subItems: List<SubItemXXX>
    )

    data class Service(
        val name: String,
        val subItems: List<SubItemXXXX>
    )

    data class Subway(
        val name: String,
        val subItems: List<SubItemXXXXX>
    )

    data class TimeRanges(
        val name: String,
        val subItems: List<SubItemXXXXXXX>
    )

    data class SubItem(
        val count: Int,
        val id: Int,
        val name: String,
        var isSelect: Boolean
    )

    data class SubItemX(
        val count: Int,
        val id: Int,
        val name: String,
        val subItems: List<SubItemXX>,
        var isSelect: Boolean
    )

    data class SubItemXX(
        val count: Int,
        val id: Int,
        val name: String,
        val subItems: List<Any>
    )

    data class SubItemXXX(
        val count: Int,
        val id: Int,
        val name: String,
        var isSelect: Boolean
    )

    data class SubItemXXXX(
        val count: Int,
        val id: Int,
        val name: String,
        var isSelect:Boolean
    )

    data class SubItemXXXXX(
        val count: Int,
        val id: Int,
        val name: String,
        val subItems: List<SubItemXXXXXX>,
        var  isSelect: Boolean
    )

    data class SubItemXXXXXX(
        val count: Int,
        val id: Int,
        val name: String,
        val subItems: List<Any>
    )

    data class SubItemXXXXXXX(
        val count: Int,
        val id: Int,
        val name: String
    )
}

