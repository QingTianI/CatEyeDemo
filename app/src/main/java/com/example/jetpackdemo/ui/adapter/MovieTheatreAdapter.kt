package com.example.jetpackdemo.ui.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import cn.jzvd.JZUtils.dip2px
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.CinemaList
import com.example.jetpackdemo.databinding.ItemMovieTheatreBinding

class MovieTheatreAdapter : BaseQuickAdapter<CinemaList.Cinema, BaseViewHolder> {
    constructor(mutableList: MutableList<CinemaList.Cinema>) : super(
        R.layout.item_movie_theatre,
        mutableList
    )


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieTheatreBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: CinemaList.Cinema) {

        BaseDataBindingHolder<ItemMovieTheatreBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()
        }


        /**
         *
         *   tabel 标签
         */

        val lv_tabel = holder.getView<LinearLayout>(R.id.lv_label)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val marginRight = dip2px(context, 4F)
        layoutParams.setMargins(0, 0, marginRight, 0)  //边距


        if (holder.itemView.tag == null) {

            if (item.labels != null && item.labels.size > 0) {


                for (label in item.labels) {

                    val tv = createTextView(label, layoutParams)
                    lv_tabel.addView(tv)
                }

            }


            holder.itemView.tag = lv_tabel


        }


    }

    private fun createTextView(
        label: CinemaList.Label,
        params: LinearLayout.LayoutParams
    ): TextView {


        return TextView(context).apply {
            layoutParams = params
            textSize = 13F   //字体大小
            text = label.name   //标签名字
            maxLines = 1   //单行
            ellipsize = TextUtils.TruncateAt.END
            setTextColor(Color.parseColor(label.color))  //字体颜色
            background = context.resources.getDrawable(R.drawable.shape_tv_label)
            val gradientDrawable = background as GradientDrawable
            gradientDrawable.setStroke(2, Color.parseColor(label.color)) //边距
        }


    }

    private fun isShowTextView(textView: TextView, isShow: Boolean, info: String = "") {

        if (isShow) {
            textView.text = info

        } else {
            textView.visibility = View.GONE


        }


    }

    private fun setCinemaLowPrice(holder: BaseViewHolder, price: Double) {


    }

    inner class Presenter {

        /**
         *
         * @param it String
         * @return String
         */
        fun tag(it: String) =
            if (TextUtils.isEmpty(it)) {
                ""
            } else {

                it
            }

        /**
         *
         * @param it String
         * @return Int
         */

        fun tagVisibility(it: String) = if (TextUtils.isEmpty(it)) {

            View.GONE
        } else {
            View.VISIBLE
        }
    }

}