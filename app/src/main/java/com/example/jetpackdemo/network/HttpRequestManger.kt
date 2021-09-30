package com.example.jetpackdemo.network


val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {
//    suspend fun getHotShow():HotMovieListBean {
//
//        return serviceApi.getHostList(10)
//
//    }
}