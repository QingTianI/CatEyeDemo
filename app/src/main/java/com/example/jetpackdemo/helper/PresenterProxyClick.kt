package com.example.jetpackdemo.helper

import android.view.View

/**
 * 管理view点击事件,具体由子类具体实现
 */
 interface PresenterProxyClick:View.OnClickListener {
    override fun onClick(v: View?)


}