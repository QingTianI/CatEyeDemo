package com.example.jetpackdemo.viewmodel.fragment

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class MovieStarHonorDetailWebViewViewModel : BaseViewModel() {
    var awardUrl: StringLiveData = StringLiveData()

}