package com.example.jetpackdemo.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.data.model.bean.PickCity
import com.example.jetpackdemo.network.apiService
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState

class CityPickerViewModel:BaseViewModel() {

     val cityLiveData  = MutableLiveData<ResultState<PickCity>>()

    fun getCity(){

        requestNoCheck({ apiService.getCity()},cityLiveData)

    }

}