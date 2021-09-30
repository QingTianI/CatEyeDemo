package com.example.jetpackdemo.custom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jetpackdemo.R
import java.util.*

/**
 *
 * 三种状态的LinearLayout，用于切换当前的页面显示
 * ERROR,CONTENT,LOADING
 */
class ProgressLayout : LinearLayout {
    private val handler = object : Handler(){}

    private var layoutParams: LayoutParams? = null
    private var layoutInflater: LayoutInflater? = null
    private var loadingView: LinearLayout? = null
    private var errorView: LinearLayout? = null
    private var btn_error: TextView? = null
    private val contentViews: MutableList<View>? =
        ArrayList()
    private var rotateAnimation: RotateAnimation? = null

    constructor(context: Context?) : super(context)

    private enum class State {
        LOADING, CONTENT, ERROR
    }

    private var currentState =
        State.LOADING

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun addView(
        child: View,
        index: Int,
        params: ViewGroup.LayoutParams
    ) {
        super.addView(child, index, params)
        if (child.tag == null || (child.tag != LOADING_TAG
                    && child.tag != ERROR_TAG)
        ) {
            contentViews!!.add(child)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (btn_error != null) {
            btn_error!!.setOnClickListener(null)
        }
    }

    fun showLoading() {
        currentState =
            State.LOADING
        showLoadingView()
        hideErrorView()
        setContentVisibility(false)
    }

    @SuppressLint("CheckResult")
    fun showContent() { //延迟500毫秒显示


        handler.postDelayed(Runnable {
            currentState =
                State.CONTENT
            setContentVisibility(true)
            hideLoadingView()
            hideErrorView()
        }, 500)


    }

    fun showError(click: OnClickListener?) {
        currentState =
            State.ERROR
        hideLoadingView()
        showErrorView()
        errorView!!.setOnClickListener(click)
        //        this.setContentVisibility(false);
        hideContentView()
    }

    val isContent: Boolean
        get() = currentState == State.CONTENT

    /**
     * 显示加载中布局
     */
    private fun showLoadingView() {
        if (loadingView == null) {
            loadingView =
                layoutInflater?.inflate(R.layout.layout_loading_view, null) as LinearLayout?
            loadingView!!.tag =
                LOADING_TAG
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            val iv_loading =
                loadingView!!.findViewById<View>(R.id.iv_loading) as ImageView
            rotateAnimation = RotateAnimation(
                0F,
                360F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            rotateAnimation!!.duration = 800
            rotateAnimation!!.repeatMode = Animation.RESTART //重复
            rotateAnimation!!.repeatCount = Animation.INFINITE //无限旋转
            rotateAnimation!!.start()
            val lir = LinearInterpolator() //线性差值器,一直匀速旋转
            rotateAnimation!!.interpolator = lir
            iv_loading.startAnimation(rotateAnimation)
            this.addView(loadingView, layoutParams)
        } else {
            rotateAnimation!!.start()
            loadingView!!.visibility = View.VISIBLE
        }
    }

    private fun showErrorView() {
        if (errorView == null) {
            errorView = layoutInflater?.inflate(R.layout.layout_error_view, null) as LinearLayout?
            errorView!!.tag =
                ERROR_TAG
//            btn_error = errorView!!.findViewById<View>(R.id.btn_try) as TextView
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            this.addView(errorView, layoutParams)
        } else {
            errorView!!.visibility = View.VISIBLE
        }
    }

    private fun hideLoadingView() {
        if (loadingView != null && loadingView!!.visibility != View.GONE) {
            loadingView!!.visibility = View.GONE
            rotateAnimation!!.cancel()
        }
    }

    private fun hideErrorView() {
        if (errorView != null && errorView!!.visibility != View.GONE) {
            errorView!!.visibility = View.GONE
        }
    }

    private fun hideContentView() {
        if (contentViews != null) {
            for (contentView in contentViews) {
                contentView.visibility = View.GONE
            }
        }
    }

    fun setContentVisibility(visible: Boolean) {
        for (contentView in contentViews!!) {
            contentView.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }

    companion object {
        private const val LOADING_TAG = "loading_tag"
        private const val ERROR_TAG = "error_tag"
    }
}