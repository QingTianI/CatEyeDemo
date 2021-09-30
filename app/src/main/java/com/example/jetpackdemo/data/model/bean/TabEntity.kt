package com.example.jetpackdemo.data.model.bean


import com.flyco.tablayout.listener.CustomTabEntity

data class TabEntity( val title :String,val tabUnIcon:Int = 0,val tabSelectIcon:Int = 0) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {


        return tabUnIcon
    }

    override fun getTabSelectedIcon(): Int {

        return tabSelectIcon
    }

    override fun getTabTitle(): String {

        return title
    }
}