package com.example.jetpackdemo.custom

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.jetpackdemo.R

class CustomNormalTitleBar : LinearLayout {

    private lateinit var toolbar_title: TextView
    private lateinit var activity: Activity

    constructor(context: Context) : super(context) {
        initView(context)

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        initView(context)


    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.title_normal, this)

        val toolbar_back = findViewById<RelativeLayout>(R.id.toolbar_back)
        activity = context as Activity
        toolbar_title = findViewById<TextView>(R.id.toolbar_title)


        /**
         *  点击事件
         */
        toolbar_back.setOnClickListener {

            activity.finish()
        }


        /**
         *  标题
         */

        toolbar_title.text = activity.title

    }

    fun setToolbarTitle(toolbar: String) {
        this.toolbar_title.text = toolbar
    }
}