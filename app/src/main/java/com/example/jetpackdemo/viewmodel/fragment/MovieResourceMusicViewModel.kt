package com.example.jetpackdemo.viewmodel.fragment

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData

class MovieResourceMusicViewModel : BaseViewModel() {
    var movieId = IntLiveData()

}