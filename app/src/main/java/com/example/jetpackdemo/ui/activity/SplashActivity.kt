package com.example.jetpackdemo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseActivity
import com.example.jetpackdemo.databinding.ActivitySplashBinding
import com.example.jetpackdemo.util.setFullWindowScreen
import com.example.jetpackdemo.viewmodel.activity.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {


    private val countDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onFinish() {

            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()

        }

        override fun onTick(millisUntilFinished: Long) {
            mDatabind.tvTimer.text = "${millisUntilFinished / 1000}s"
        }
    }

    override fun layoutId(): Int {

        return R.layout.activity_splash

    }




    override fun createObserver() {
    }


    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    override fun initTitle() {


    }

    override fun initData(savedInstanceState: Bundle?) {
        /**
         *  设置全屏
         */
        setFullWindowScreen(this)
        /**
         * 开启计时器
         */
        countDownTimer.start()
    }

}