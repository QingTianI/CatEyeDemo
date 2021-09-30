package com.example.jetpackdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.MovieStarInfo
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class MovieStarDetailViewModel : BaseViewModel() {
    val movieStarInfo = MutableLiveData<MovieStarInfo>()
}