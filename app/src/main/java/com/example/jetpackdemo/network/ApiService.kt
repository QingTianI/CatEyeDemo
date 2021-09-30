package com.example.jetpackdemo.network

import com.example.jetpackdemo.data.model.bean.*
import retrofit2.http.*

interface ApiService {
    companion object {
        const val SERVER_URL = "http://api.maoyan.com/"
    }

    //获取首页热映列表
    @GET("mmdb/movie/v3/list/hot.json")
    suspend fun getHostList(@Query("limit") limit: Int): HotMovieList

    //加载更多的数据
    @GET("mmdb/movie/list/info.json")
    suspend fun getMoreHotMovieList(
        @Query("headline") headline: Int,
        @Query("movieIds") movieIds: String
    ): HotMovieList


    //获取城市
    @GET("dianying/cities.json")
    suspend fun getCity(): PickCity


    //电影基本信息
    @GET("mmdb/movie/v5/{movieId}.json")
    suspend fun getMovieBasicData(@Path("movieId") movieId: Int): MovieBasicData


    //演员列表
    @GET("mmdb/v7/movie/{movieId}/celebrities.json")
    suspend fun getMovieStarList(@Path("movieId") movieId: Int): MovieStar

    //演员信息详情    mmdb/v6/celebrity/7194.json
    @GET(" mmdb/v6/celebrity/{movieStarId}.json")
    suspend fun getMovieStarInfoBean(@Path("movieStarId") movieStarId: Int): MovieStarInfo

    //影人荣誉
    @GET("mmdb/celebrity/{starId}/honors.json")
    suspend fun getMovieStarHonors(@Path("starId") starId: Int): MovieStarHonor

    //参演电影
    @GET("mmdb/celebrity/{starId}/rank/movies.json")
    suspend fun getStarMovies(
        @Path("starId") starId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): StarMovies

    //相关影人
    @GET("mmdb/celebrity/{starId}/relationship.json")
    suspend fun getStarRelatedPeople(@Path("starId") starId: Int): StarRelatedPeople

    //视频预告片
    @GET("mmdb/v1/movie/{movieId}/videos.json")
    suspend fun getPreviewVideoList(
        @Path("movieId") movieId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): VideoPreview

    //影片技术参数
    @GET("mmdb/movie/{movieId}/feature/v1/technicals.json")
    suspend fun getMovieTechnicals(@Path("movieId") movieId: Int): MovieTechnicals

    //出品发行
    @GET("mmdb/movie/{movieId}/feature/relatedCompanies.json")
    suspend fun getMovieRelatedCompanies(@Path("movieId") movieId: Int): MovieRelatedCompanies

    //影片资料
    @GET("mmdb/movie/{movieId}/feature/v2/list.json")
    suspend fun getMovieResource(@Path("movieId") movieId: Int): MovieResource


    // 票房
    @GET("mmdb/movie/{movieId}/feature/v1/mbox.json")
    suspend fun getBoxOffice(@Path("movieId") movieId: Int): BoxOffice

    //    电影相关快讯
    @GET("sns/news/v3/type/0/target/{movieId}/top.json")
    suspend fun getMovieRelatedInfo(@Path("movieId") movieId: Int): MovieRelatedNews

    //相关电影
    @GET("mmdb/movie/{movieId}/feature/relatedFilm.json")
    suspend fun getRelatedMovies(@Path("movieId") movieId: Int): RelatedMovies


    //电影相关信息
    @GET("sns/news/v3/type/0/target/{movieId}.json")
    suspend fun getMovieRelatedNews(
        @Path("movieId") movieId: Int, @Query("limit") limit: Int, @Query("offset") offset: Int
    ): MovieRelatedNews

    //电影短评标签
    @GET("mmdb/comment/tag/movie/{movieId}.json")
    suspend fun getMovieCommentTag(@Path("movieId") movieId: Int): MovieCommentTag

    //电影短评详情
    @GET("mmdb/comments/movie/v3/{movieId}.json")
    suspend fun getMovieCommentTagDetailed(
        @Path("movieId") movieId: Int,
        @Query("tag") tag: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MovieCommentTagDetailed

    //热门长评
    @GET("sns/movie/{movieId}/filmReview/top.json")
    suspend fun getMovieLongComment(@Path("movieId") movieId: Int): MovieLongComment

    //全部热门长评
    @GET("sns/movie/{movieId}/filmReviews.json")
    suspend fun getMovieLongCommentList(
        @Path("movieId") movieId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MovieLongCommentList


    //待映-预告片推荐
    @GET("mmdb/movie/lp/list.json")
    suspend fun getPreviewedRecommend(): PreviewedRecommend

    //待映- 待映推荐
    @GET("mmdb/movie/v1/list/wish/order/coming.json")
    suspend fun getExpectMovieList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ExpectMovie

    //待映-列表
    @GET("mmdb/movie/v2/list/rt/order/coming.json")
    suspend fun getWaitMovieList(@Query("limit") limit: Int): WaitMovie

    //获取筛选信息
    @Headers(
        "Date:Wed, 21 Jun 2017 13:49:41 GMT",
        "Key:91137528",
        "userid:-1",
        "Authorization:7d125f9728d3f7ad8b73f0a57fbf938d",
        "if-Modified-Since:Wed, 21 Jun 2017 13:49:41 GMT"
    )
    @GET("mmcs/cinema/v1/select/items.json")
    suspend fun getFilter(@Query("cityId") cityId: Int): Filter

    //获取电影院,这4个参数必须有,否则会提示没有授权,
    //Authorization 的值是根据Date和其他约定规则生成,但是无法得知生成规则,所以只能根据抓包内容写死
    @Headers(
        "Date:Wed, 21 Jun 2017 13:49:41 GMT",
        "Key:91137528",
        "userid:-1",
        "Authorization:7d125f9728d3f7ad8b73f0a57fbf938d",
        "if-Modified-Since:Wed, 21 Jun 2017 13:49:41 GMT"
    )
    @GET("mmcs/cinema/v1/select/cinemas.json")
    suspend fun getCinemaList(@QueryMap queryMap: Map<String, Any>): CinemaList


    @Headers(
        "Date:Wed, 21 Jun 2017 13:49:41 GMT",
        "Key:91137528",
        "userid:-1",
        "Authorization:7d125f9728d3f7ad8b73f0a57fbf938d",
        "if-Modified-Since:Wed, 21 Jun 2017 13:49:41 GMT"
    )
    @GET("mmcs/cinema/v1/select/cinemas.json")
    suspend fun getCinemaListByNormal(
        @Query("utm_term") utm_term: String,
        @Query("offset") offset: Int,
        @Query("lng") lng: Double,
        @Query("utm_medium") utm_medium: String,
        @Query("brandId") brandId: Int,
        @Query("limit") limit: Int,
        @Query("hallType") hallType: Int,
        @Query("cityId") cityId: Int,
        @Query("sort") sort: String,
        @Query("serviceId") serviceId: Int,
        @Query("channelId") channelId: Int,
        @Query("lat") lat: Double

    ): CinemaList

    @Headers(
        "Date:Wed, 21 Jun 2017 13:49:41 GMT",
        "Key:91137528",
        "userid:-1",
        "Authorization:7d125f9728d3f7ad8b73f0a57fbf938d",
        "if-Modified-Since:Wed, 21 Jun 2017 13:49:41 GMT"
    )
    @GET("mmcs/cinema/v1/select/cinemas.json")
    suspend fun getCinemaListByDistrict(
        @Query("offset") offset: Int,
        @Query("lng") lng: Double,
        @Query("utm_medium") utm_medium: String,
        @Query("hallType") hallType: Int,
        @Query("cityId") cityId: Int,
        @Query("sort") sort: String,
        @Query("utm_term") utm_term: String,
        @Query("districtId") districtId: Int,
        @Query("areaId") areaId: Int,
        @Query("brandId") brandId: Int,
        @Query("limit") limit: Int,
        @Query("serviceId") serviceId: Int,
        @Query("channelId") channelId: Int,
        @Query("lat") lat: Double

    ): CinemaList


    @Headers(
        "Date:Wed, 21 Jun 2017 13:49:41 GMT",
        "Key:91137528",
        "userid:-1",
        "Authorization:7d125f9728d3f7ad8b73f0a57fbf938d",
        "if-Modified-Since:Wed, 21 Jun 2017 13:49:41 GMT"
    )
    @GET("mmcs/cinema/v1/select/cinemas.json")
    suspend fun getCinemaListBySubway(
        @Query("offset") offset: Int,
        @Query("lng") lng: Double,
        @Query("utm_medium") utm_medium: String,
        @Query("hallType") hallType: Int,
        @Query("lineId") lineId: Int,
        @Query("cityId") cityId: Int,
        @Query("sort") sort: String,
        @Query("utm_term") utm_term: String,
        @Query("brandId") brandId: Int,
        @Query("limit") limit: Int,
        @Query("serviceId") serviceId: Int,
        @Query("channelId") channelId: Int,
        @Query("lat") lat: Double,
        @Query("stationId") stationId: Int

    ): CinemaList

}