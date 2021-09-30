package com.example.jetpackdemo.util

import android.content.Context
import android.graphics.Color
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat
import com.example.jetpackdemo.R

object SettingUtil {
    /**
     * 获取当前主题颜色
     */
    fun getColor(context: Context): Int {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else {
            color
        }

    }



}