package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class MovieStarPhotoDetailsViewModel : BaseViewModel() {
    val movieName = StringLiveData()
    val photos = MutableLiveData<List<String>>()
}