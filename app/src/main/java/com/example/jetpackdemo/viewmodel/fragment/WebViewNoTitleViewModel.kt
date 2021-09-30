package com.example.jetpackdemo.viewmodel.fragment

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class WebViewNoTitleViewModel : BaseViewModel() {
    var webViewUrl: StringLiveData = StringLiveData()
}