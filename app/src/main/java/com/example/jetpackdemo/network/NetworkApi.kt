package com.example.jetpackdemo.network

import com.google.gson.GsonBuilder
import me.hgj.jetpackmvvm.network.BaseNetworkApi
import me.hgj.jetpackmvvm.network.CoroutineCallAdapterFactory
import me.hgj.jetpackmvvm.network.interceptor.logging.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//双重校验锁式-单例 封装NetApiService 方便直接快速调用//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口

//双重校验锁式-单例 封装NetApiService 方便直接快速调用
val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.instance.getApi(ApiService::class.java, ApiService.SERVER_URL)
}

class NetworkApi :BaseNetworkApi(){

    companion object {

        val instance: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { NetworkApi() }



    }
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {

        builder.apply {
            //示例：添加公共heads，可以存放token，公共参数等， 注意要设置在日志拦截器之前，不然Log中会不显示head信息
//            addInterceptor(MyHeadInterceptor())
            // 日志拦截器
            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder

    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {


        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }
    }


}