package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews
import com.example.jetpackdemo.databinding.ItemMovieRelatedNewsSingleBinding

class MovieRelatedNewsSingleProvider:BaseItemProvider<MovieRelatedNews.News>() {
    override val itemViewType: Int
        get() =  BaseMultiType.TYPE_MOVIE_RELATED_NEWS_SINGLE

    override val layoutId: Int
        get() = R.layout.item_movie_related_news_single

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieRelatedNewsSingleBinding>(viewHolder.itemView)
    }
    override fun convert(helper: BaseViewHolder, item: MovieRelatedNews.News) {

        BaseDataBindingHolder<ItemMovieRelatedNewsSingleBinding>(helper.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }


    }
}