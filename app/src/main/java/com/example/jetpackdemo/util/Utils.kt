package com.example.jetpackdemo.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.ui.fragment.*
import com.example.jetpackdemo.ui.fragment.module.SmallVideo.SmallVideoFragment
import com.example.jetpackdemo.ui.fragment.module.cinema.*
import com.example.jetpackdemo.ui.fragment.module.home.HomeFragment
import com.example.jetpackdemo.ui.fragment.module.mine.MineFragment
import com.example.jetpackdemo.ui.fragment.module.show.ShowFragment
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.util.layoutInflater
import me.hgj.jetpackmvvm.ext.util.loge
import me.hgj.jetpackmvvm.network.AppException


var mToast: Toast? = null


fun showErrorMessage(appException: AppException) {

    appException.errorMsg.loge()
}

/**
 * @receiver RecyclerView
 * @param adapter BaseQuickAdapter<T, BaseViewHolder>
 * @param context Context
 * @param orientation Int
 */
fun <T> RecyclerView.loadRecyclerAdapterByLinearLayoutManager(
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    context: Context,
    DefaultOrientation: Int = RecyclerView.VERTICAL

) {


    val linearLayoutManager = LinearLayoutManager(context, DefaultOrientation, false)
    layoutManager = linearLayoutManager
    this.adapter = adapter

}

/**
 *
 * @receiver RecyclerView
 * @param adapter BaseQuickAdapter<T, BaseViewHolder>
 * @param context Context
 * @param spanCount Int
 */
fun <T> RecyclerView.loadRecyclerAdapterByGridLayoutManager(
    adapter: BaseQuickAdapter<T, BaseViewHolder>,
    context: Context,
    spanCount: Int

) {
    val gridLayoutManager = GridLayoutManager(context, spanCount)

    layoutManager = gridLayoutManager
    this.adapter = adapter


}

fun countWishPeople(wish: Int): String {


    if (wish > 10000) {


        val count1 = wish / 10000         //单位 :万
        val remainder = wish % 10000    //取模  取余数
        val count2 = remainder / 1000               //单位 :千
        return "${count1}.${count2}万"

    } else {

        return "${wish}"


    }


}


fun makeToast(context: Context?, string: String?) {
    if (mToast == null) {
        mToast = Toast.makeText(context, string, Toast.LENGTH_SHORT)
    }

    mToast?.setText(string)
    mToast?.show()

}


fun setFullWindowScreen(activity: Activity) {

    if (Build.VERSION.SDK_INT >= 21) {
        val decorView = activity.window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        activity.window.statusBarColor = Color.TRANSPARENT
    }


}


fun countWatchedAndWishCount(peopleCount: Int): String {


    /**
     *
     * 4269357          4267935
     * 4,269,357          267,935
     */


    val peopleCountToString = peopleCount.toString()
    val totalLength = peopleCountToString.length  //长度

    val remainder = totalLength % 3  //余数    0
    val merchant = totalLength / 3    //截取次数    2


    val stringBuilder = StringBuilder()

    //        判断是否有余数
    if (remainder != 0) {

        //            添加头部头部字段
        stringBuilder.append(peopleCountToString.substring(0, remainder)).append(",")

    }


    var startIndex = 0 + remainder     // 截取字段开始位置
    var endIndex = 3 + remainder       //  截取字段结束位置


    //       添加尾部字段
    for (i in 0 until merchant) {

        val string = peopleCountToString.substring(startIndex, endIndex)
        endIndex += 3
        startIndex += 3

        //            判断是否为最后一位
        if (i < merchant - 1) {
            stringBuilder.append(string)
            stringBuilder.append(",")
        } else {
            stringBuilder.append(string)
        }


    }

    return stringBuilder.toString()


}


/**
 *
 * @receiver WebView
 * @param webViewUrl String
 */
fun WebView.loadWebView(webViewUrl: String) {

    val settings = settings
    settings.javaScriptEnabled = true
    webViewClient = WebViewClient()
    loadUrl(webViewUrl)
}


///**
// *
// * @receiver Toolbar
// * @param activity Activity
// * @param defaultTitle String
// * @param defaulBackgroundColor Int
// * @return Toolbar
// */
//fun Toolbar.setWebViewTitle(
//    activity: Activity,
//    fragment: Fragment,
//    defaultTitle: String,
//    defaultBackgroundColor: Int = SettingUtil.getColor(appContext)
//
//): Toolbar {
//
//    val toolbar_title = activity.findViewById<TextView>(R.id.toolbar_title)
//    val toolbar_back = activity.findViewById<ImageView>(R.id.toolbar_back)
//
//    setBackgroundColor(defaultBackgroundColor)
//    toolbar_title.text = defaultTitle
//    toolbar_back.setOnClickListener {
//
//        //返回上一页
//        fragment.nav().navigateUp()
//    }
//    return this
//
//}


/**
 *
 * @receiver Toolbar
 * @param activity Activity
 * @param fragment Fragment
 * @param defaultTitle String
 * @param defaultBackgroundColor Int
 * @return Toolbar
 */
//fun Toolbar.setCustomTitle(
//    activity: Activity,
//    fragment: Fragment,
//    defaultTitle: String,
//    defaultBackgroundColor: Int = SettingUtil.getColor(appContext),
//
//    ): Toolbar {
//
//    val toolbar_title = activity.findViewById<TextView>(R.id.toolbar_title)
//    val toolbar_back = activity.findViewById<ImageView>(R.id.toolbar_back)
//
//    setBackgroundColor(defaultBackgroundColor)
//    toolbar_title.text = defaultTitle
//    toolbar_back.setOnClickListener {
//
//        //返回上一页
//        fragment.nav().navigateUp()
//    }
//
//    return this
//
//}


/**
 *
 * @receiver ViewPager2
 * @param fragment Fragment
 * @return ViewPager2
 */

fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 5
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                1 -> {
                    return CinemaFragment()
                }
                2 -> {
                    return SmallVideoFragment()
                }
                3 -> {
                    return ShowFragment()
                }
                4 -> {
                    return MineFragment()
                }
                else -> {
                    return CinemaFragment()
                }
            }
        }

        override fun getItemCount() = 5
    }
    return this
}


fun BottomNavigationViewEx.init(navigationItemSelectedAction: (Int) -> Unit): BottomNavigationViewEx {
    enableAnimation(true)
    enableShiftingMode(false)
    enableItemShiftingMode(true)
    setTextSize(12F)
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}

fun Toolbar.setNormalTitle(
    fragment: Fragment,
    defaultTitle: String,
    defaultBackgroundColor: Int = SettingUtil.getColor(appContext),


    ): Toolbar {

    val inflate = appContext.layoutInflater?.inflate(R.layout.include_normal_title, this)


    inflate?.let {

        val toolbar_back = findViewById<ImageView>(R.id.toolbar_back)
        val toolbar_title = findViewById<TextView>(R.id.toolbar_title)

        toolbar_title.text = defaultTitle

        toolbar_back.setOnClickListener {

            fragment.nav().navigateUp()

        }


    }

    setBackgroundColor(defaultBackgroundColor)

    return this

}

fun Toolbar.setShortCommentTitle(
    fragment: Fragment,
    defaultTitle: String,
    tvCommentTitle: String,
    defaultBackgroundColor: Int = SettingUtil.getColor(appContext),


    ): Toolbar {

    val inflate = appContext.layoutInflater?.inflate(R.layout.include_short_comment_title, this)


    inflate?.let {

        val toolbar_back = findViewById<ImageView>(R.id.toolbar_back)
        val toolbar_title = findViewById<TextView>(R.id.toolbar_title)
        val tv_comment_title = findViewById<TextView>(R.id.tv_comment_title)

        toolbar_title.text = defaultTitle
        tv_comment_title.text = tvCommentTitle

        toolbar_back.setOnClickListener {

            fragment.nav().navigateUp()

        }


    }

    setBackgroundColor(defaultBackgroundColor)

    return this

}

fun Toolbar.setLoneLommentTitle(
    fragment: Fragment,
    defaultTitle: String,
    defaultBackgroundColor: Int = SettingUtil.getColor(appContext),


    ): Toolbar {

    val inflate = appContext.layoutInflater?.inflate(R.layout.include_lone_comment_title, this)


    inflate?.let {

        val toolbar_back = findViewById<ImageView>(R.id.toolbar_back)
        val toolbar_title = findViewById<TextView>(R.id.toolbar_title)

        toolbar_title.text = defaultTitle

        toolbar_back.setOnClickListener {

            fragment.nav().navigateUp()

        }


    }

    setBackgroundColor(defaultBackgroundColor)

    return this

}

fun Toolbar.setNoBackTitle(
    defaultTitle: String,
    defaultBackgroundColor: Int = SettingUtil.getColor(appContext),


    ): Toolbar {

    val inflate = appContext.layoutInflater?.inflate(R.layout.include_lone_comment_title, this)


    inflate?.let {

        val toolbar_title = findViewById<TextView>(R.id.toolbar_title)

        toolbar_title.text = defaultTitle


    }

    setBackgroundColor(defaultBackgroundColor)

    return this

}


/**
 *
 * @param int Number
 * @return String
 */
fun toString(number: Number): String {

    return number.toString()

}

/**
 *
 * @param url String?
 * @return String
 */
fun resetPicUrl(url: String?): String {

    var imgUrl = ""

    url?.let {

        if (url.contains("@")) {
            val origin = url.substring(0, url.indexOf("@"))
            imgUrl = origin.replace("/w.h/", "/")
        }

        if (!url.contains("/w.h/")) {

            imgUrl = url
        } else {

            imgUrl = url.replace("/w.h/", "/")
        }


    }

    return imgUrl
}

/**
 *
 * @param userLevel Int
 * @return Drawable
 */
fun setUserLevel(userLevel: Int): Drawable {


    var drawable = appContext.resources.getDrawable(R.drawable.level_1)

    when (userLevel) {

        1 -> drawable = appContext.resources.getDrawable(R.drawable.level_1)
        2 -> drawable = appContext.resources.getDrawable(R.drawable.level_2)
        3 -> drawable = appContext.resources.getDrawable(R.drawable.level_3)
        4 -> drawable = appContext.resources.getDrawable(R.drawable.level_4)
        5 -> drawable = appContext.resources.getDrawable(R.drawable.level_5)
        6 -> drawable = appContext.resources.getDrawable(R.drawable.level_6)


    }


    return drawable
}

/**
 *
 * @param boolean Boolean
 * @return Int
 */
fun setLevelVisibility(boolean: Boolean) = if (boolean) View.VISIBLE else View.GONE

/**
 *
 * @param number Number
 * @return Int
 */
fun toInt(number: Number) = number.toInt()

/**
 *
 * @param activity AppCompatActivity
 * @return Fragment?
 */
fun getFragmentByFragmentManager(activity: AppCompatActivity): Fragment? {


    val navHostFragment =
        activity.supportFragmentManager.findFragmentById(R.id.host_fragment)


    return navHostFragment?.run {

        childFragmentManager.primaryNavigationFragment


    }


}