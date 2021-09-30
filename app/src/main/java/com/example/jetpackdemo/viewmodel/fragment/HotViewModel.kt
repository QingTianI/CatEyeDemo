package com.example.jetpackdemo.viewmodel.fragment

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.IntLiveData

class HotViewModel : BaseViewModel() {


    var countLiveData = IntLiveData()

    fun plus() {

        countLiveData.value =  (countLiveData.value) + 1
    }

}