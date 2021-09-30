package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.CinemaList
import com.example.jetpackdemo.data.model.bean.Filter
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class MovieTheatreViewModel : BaseViewModel() {


    var offY = 0

    //    请求参数     Map
    val cityId = IntLiveData()
    val utm_term = "7.9.1"
    var offset = 0
    val lng = -122.083977
    val utm_medium = "android"
    var brandId = -1     //  品牌
    val limit = 10
    var hallType = -1    //特效厅

    var sort = "distance"
    var serviceId = -1  //特色功能
    val channelId = 1
    val lat = 37.421948

    var districtId = -1    //地区Id,全部为-1
    var areaId = -1     //详细区域id
    var lineId = -1      //地铁id,全部为-1
    var stationId = -1


    //position
    var prePositionService = 0   //上一个
    var selectPositionService = 0 //选中的

    var prePositionHalfType = 0  //上一个
    var selectPositionHalfType = 0  //选中的

    var prePositionBrand = 0
    var prePositionDistance = 0
    var prePositionCityAreaParent = 0
    var prePositionSubwayParent = 0

    var tv_brandIsClick = true
    var tv_whole_cityIsClick = true
    var tv_short_distanceIsClick = true
    var tv_chooseIsClick = true
    var areaIsClick = false
    var stationIsClick = false


    /**
     * 网络数据封装
     */
    val cinemaList = MutableLiveData<ResultState<CinemaList>>()
    val filter = MutableLiveData<ResultState<Filter>>()


    fun getCinemaListByNormal(
        utm_term: String,
        offset: Int,
        lng: Double,
        utm_medium: String,
        brandId: Int,
        limit: Int,
        hallType: Int,
        cityId: Int,
        sort: String,
        serviceId: Int,
        channelId: Int,
        lat: Double
    ) {

        requestNoCheck({
            apiService.getCinemaListByNormal(
                utm_term,
                offset,
                lng,
                utm_medium,
                brandId,
                limit,
                hallType,
                cityId,
                sort,
                serviceId,
                channelId,
                lat
            )
        }, cinemaList)

    }

    fun getFilterCondition(cityId: Int) {

        requestNoCheck({ apiService.getFilter(cityId) }, filter)
    }

    fun getCinemaListByDistrict(
        offset: Int,
        lng: Double,
        utm_medium: String,
        hallType: Int,
        cityId: Int,
        sort: String,
        utm_term: String,
        districtId: Int,
        areaId: Int,
        brandId: Int,
        limit: Int,
        serviceId: Int,
        channelId: Int,
        lat: Double
    ) {

        requestNoCheck({
            apiService.getCinemaListByDistrict(
                offset,
                lng,
                utm_medium,
                hallType,
                cityId,
                sort,
                utm_term,
                districtId,
                areaId,
                brandId,
                limit,
                serviceId,
                channelId,
                lat
            )
        }, cinemaList)

    }

    fun getCinemaListBySubway(
        offset: Int,
        lng: Double,
        utm_medium: String,
        hallType: Int,
        lineId: Int,
        cityId: Int,
        sort: String,
        utm_term: String,
        brandId: Int,
        limit: Int,
        serviceId: Int,
        channelId: Int,
        lat: Double,
        stationId: Int
    ) {


        requestNoCheck({
            apiService.getCinemaListBySubway(
                offset,
                lng,
                utm_medium,
                hallType,
                lineId,
                cityId,
                sort,
                utm_term,
                brandId,
                limit,
                serviceId,
                channelId,
                lat,
                stationId
            )
        }, cinemaList)

    }



}