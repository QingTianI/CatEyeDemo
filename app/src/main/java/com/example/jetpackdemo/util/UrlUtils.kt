package com.example.jetpackdemo.util

import androidx.annotation.NonNull

object UrlUtils {

    //通过替换w.h获取图片
    fun processUrl(@NonNull url: String, width: Int, height: Int): String? {
        return url.replace("/w.h/", "/$width.$height/")
    }

    fun getRealWebViewUrl(url: String): String {


        if (url.contains("id=")) {
            return if (url.contains("&")) {
                val id = url.substring(url.indexOf("id=") + 3, url.indexOf("&"))
                "http://m.maoyan.com/information/$id?_v_=yes"
            } else {
                val id = url.substring(url.indexOf("id=") + 3)
                "http://m.maoyan.com/information/$id?_v_=yes"
            }
        } else if (url.contains("ID=")) {
            return if (url.contains("&")) {
                val id = url.substring(url.indexOf("ID=") + 3, url.indexOf("&"))
                "http://m.maoyan.com/topic/$id?_v_=yes"
            } else {
                val id = url.substring(url.indexOf("ID=") + 3)
                "http://m.maoyan.com/topic/$id?_v_=yes"
            }
        }
        return Exception("Error Url").toString()
    }


}