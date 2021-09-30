package com.example.jetpackdemo.viewmodel.fragment

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class WebViewViewModel : BaseViewModel() {
    val movieName  = StringLiveData()
    val webViewUrl  = StringLiveData()


}