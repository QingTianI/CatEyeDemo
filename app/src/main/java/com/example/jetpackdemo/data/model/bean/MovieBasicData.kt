package com.example.jetpackdemo.data.model.bean


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class MovieBasicData(
    val `data`: Data
) : Parcelable {

    data class Data(
        val movie: Movie
    ) : Serializable

    @Parcelize
    data class Movie(
        val availableEpisodes: Int,
        val awardUrl: String,
        val backgroundColor: String,
        val bingeWatch: Int,
        val bingeWatchst: Int,
        val cat: String,
        val comScorePersona: Boolean,
        val commented: Boolean,
        val dir: String,
        val distributions: List<Distribution>,
        val dra: String,
        val dur: Int,
        val egg: Boolean,
        val enm: String,
        val episodeDur: Int,
        val episodes: Int,
        val filmAlias: String,
        val fra: String,
        val frt: String,
        val globalReleased: Boolean,
        val guideToWish: Boolean,
        val id: Int,
        var img: String,
        val latestEpisode: Int,
        val modcsSt: Boolean,
        val movieType: Int,
        val multiPub: Boolean,
        val musicName: String,
        val musicNum: Int,
        val musicStar: String,
        val nm: String,
        val onSale: Boolean,
        val onlinePlay: Boolean,
        val orderSt: Int,
        val oriLang: String,
        val photos: List<String>,
        val pn: Int,
        val preScorePersona: Boolean,
        val proScore: Int,
        val proScoreNum: Int,
        val pubDate: Long,
        val pubDesc: String,
        val rt: String,
        val sc: Double,
        val scm: String,
        val scoreLabel: String,
        val shareInfo: ShareInfo,
        val showst: Int,
        val snum: Int,
        val src: String,
        val star: String,
        val type: Int,
        val vd: String,
        val ver: String,
        val videoImg: String,
        val videoName: String,
        val videourl: String,
        val viewedSt: Int,
        val vnum: Int,
        val vodFreeSt: Int,
        val vodPlay: Boolean,
        val vodSt: Int,
        val watched: Int,
        val wish: Int,
        val wishst: Int
    ) :  Parcelable

    data class Distribution(
        val movieScoreLevel: String,
        val proportion: String
    ) : Serializable

    data class ShareInfo(
        val channel: Int,
        val content: String,
        val img: String,
        val title: String,
        val url: String
    ) : Serializable
}


/**
 * {
"data": {
"movie": {
"availableEpisodes": 0,
"awardUrl": "",
"backgroundColor": "#3E5466",
"bingeWatch": 0,
"bingeWatchst": 0,
"cat": "剧情,悬疑,动作",
"comScorePersona": false,
"commented": false,
"dir": "张艺谋",
"distributions": [
{
"movieScoreLevel": "9-10分",
"proportion": "83.21"
},
{
"movieScoreLevel": "5-8分",
"proportion": "14.91"
},
{
"movieScoreLevel": "1-4分",
"proportion": "1.89"
}
],
"dra": "上世纪三十年代，四位曾在苏联接受特训的共产党特工组成任务小队，回国执行代号为“乌特拉”的秘密行动。由于叛徒的出卖，他们从跳伞降落的第一刻起， 就已置身于敌人布下的罗网之中。同志能否脱身，任务能否完成，雪一直下，立于“悬崖之上”的行动小组面临严峻考验。",
"dur": 120,
"egg": false,
"enm": "Cliff Walkers",
"episodeDur": 120,
"episodes": 0,
"fra": "美国",
"frt": "2021-04-30",
"globalReleased": false,
"guideToWish": false,
"id": 1298367,
"img": "http://p0.meituan.net/w.h/movie/3ad18f011110130e927d50046fde86f71038961.jpg",
"latestEpisode": 0,
"modcsSt": false,
"movieType": 0,
"multiPub": true,
"musicNum": 0,
"nm": "悬崖之上",
"onSale": true,
"onlinePlay": false,
"orderSt": 0,
"oriLang": "国语",
"photos": [
"http://p0.meituan.net/w.h/movie/4f3cf14503aa520ef470636611a45254555635.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/f5529a5d291e1fafa58510ab9cfb5766614384.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/02033e6ea5c2982b86b0dadae3736078422017.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/c57b5550a640a72c0b6a6a2037bb3a64446305.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/0057ef3f57a674d259e7b7f22ec6d38c418523.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/df0a950700573544b490f53414e4cba3632338.jpg@2500w_2500h_1l_0e",
"http://p1.meituan.net/w.h/movie/c12e81cdcc5f9958284ab0b48a60570b520670.jpg@2500w_2500h_1l_0e",
"http://p1.meituan.net/w.h/movie/195e8d1aac2c3516e9c601f420be1ad5399982.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/efa5f3a8bfda5360d039422e01fdb730450273.jpg@2500w_2500h_1l_0e",
"http://p1.meituan.net/w.h/movie/518f54eb832839f816532548b2e3a7c6578410.jpg@2500w_2500h_1l_0e",
"http://p1.meituan.net/w.h/movie/b1b2b785bc44565b55fffdf4c9e0e661608178.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/01631b6a7c3e12aec3af0e01e75948e4657149.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/5df10c8234d42db515e404187f1491ef680516.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/6bff1d24ddf3b38ef5b64cea196f3b78521465.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/4e2d084ed4ecadf5b48b3082775bae09812723.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/a94846b48cd5cca26f7685aa4c2fd93b274365.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/2a8dc848e1ec885921132a39b067a240511678.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/6390b53b438792edac1fd45575e46eb2591972.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/36e80f8befd8cf6690aadb697d50ee64419380.jpg@2500w_2500h_1l_0e",
"http://p0.meituan.net/w.h/movie/7bbf59519678da6d44c49d8c2d598b1f432177.jpg@2500w_2500h_1l_0e"
],
"pn": 167,
"preScorePersona": true,
"proScore": 0,
"proScoreNum": 0,
"pubDate": 1619712000000,
"pubDesc": "2021-04-30 18:00中国大陆上映",
"rt": "2021-04-30",
"sc": 9.2,
"scm": "张译、于和伟率戏骨团生死对决",
"scoreLabel": "猫眼点映评分",
"shareInfo": {
"channel": 1,
"content": "简介|上世纪三十年代，四位曾在苏联接受特训的共产党特工组成任务小队，回国执行代号为“乌特拉”的秘密行动。由于叛徒的出卖，他们从跳伞降落的第一刻起， 就已置身于敌人布下的罗网之中。同志能否脱身，任务能否完成，雪一直下，立于“悬崖之上”的行动小组面临严峻考验。",
"img": "http://p0.meituan.net/w.h/movie/3ad18f011110130e927d50046fde86f71038961.jpg",
"title": "《悬崖之上》猫眼点映评分9.2，2021-04-30 18:00中国大陆上映",
"url": "https://m.maoyan.com/asgard/movie/1298367"
},
"showst": 4,
"snum": 8255,
"src": "中国大陆",
"star": "张译,于和伟,秦海璐",
"type": 0,
"vd": "https://vod.pipi.cn/fec9203cvodtransbj1251246104/38e680355285890817446280928/v.f42905.mp4",
"ver": "IMAX 2D/杜比影院 2D/中国巨幕2D/CINITY 2D/2D",
"videoImg": "https://obj.pipi.cn/friday/5bb87a74dcf8c6af5b2228a8164c7921.jpeg",
"videoName": "张译于和伟演绎残酷谍战 《悬崖之上》无名英雄牺牲换黎明",
"videourl": "https://vod.pipi.cn/fec9203cvodtransbj1251246104/38e680355285890817446280928/v.f42905.mp4",
"viewedSt": 0,
"vnum": 12,
"vodFreeSt": 0,
"vodPlay": false,
"vodSt": 0,
"watched": 6486,
"wish": 125381,
"wishst": 0
}
}
}
 */
