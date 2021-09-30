package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews
import com.example.jetpackdemo.databinding.ItemMovieRelatedNewsListBinding

class MovieRelatedInfoAdapter : BaseQuickAdapter<MovieRelatedNews.News, BaseViewHolder> {


    constructor(mutableList: MutableList<MovieRelatedNews.News>) : super(
        R.layout.item_movie_related_news_list,
        mutableList
    )



    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieRelatedNewsListBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieRelatedNews.News) {

        BaseDataBindingHolder<ItemMovieRelatedNewsListBinding>(holder.itemView).dataBinding?.apply {

            this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()
        }


    }

    inner class Presenter {

        fun tvPT(createdTime: Long): String {

            val currentTime = System.currentTimeMillis()
            val pastTime = currentTime - createdTime    //时间差         单位毫秒
            val dayMillis = 1000 * 60 * 60 * 24      //一天               单位毫秒
            val day = pastTime / dayMillis             //              单位一天

            return "猫眼时间${day}天前"


        }
    }
}