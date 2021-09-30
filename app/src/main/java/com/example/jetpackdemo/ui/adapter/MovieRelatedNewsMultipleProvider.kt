package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews
import com.example.jetpackdemo.databinding.ItemMoviesRelatedNewsMultipleBinding

class MovieRelatedNewsMultipleProvider:BaseItemProvider<MovieRelatedNews.News>() {
    override val itemViewType: Int
        get() = BaseMultiType.TYPE_MOVIE_RELATED_NEWS_Multiple

    override val layoutId: Int
        get() = R.layout.item_movies_related_news_multiple

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMoviesRelatedNewsMultipleBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: MovieRelatedNews.News) {

        BaseDataBindingHolder<ItemMoviesRelatedNewsMultipleBinding>(helper.itemView).dataBinding?.apply {

            this.data = item
            this.executePendingBindings()
        }

    }
}