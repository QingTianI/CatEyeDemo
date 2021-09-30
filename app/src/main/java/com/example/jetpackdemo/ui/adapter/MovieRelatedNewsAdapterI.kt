package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews

class MovieRelatedNewsAdapterI : BaseProviderMultiAdapter<MovieRelatedNews.News> {

    constructor(mutableList: MutableList<MovieRelatedNews.News>) : super(mutableList) {

        addItemProvider(MovieRelatedNewsMultipleProvider())
        addItemProvider(MovieRelatedNewsSingleProvider())
    }

    override fun getItemType(data: List<MovieRelatedNews.News>, position: Int): Int {

        val data = data[position]

        return if (data.previewImages.size >= 3) {
            BaseMultiType.TYPE_MOVIE_RELATED_NEWS_Multiple
        } else {

            BaseMultiType.TYPE_MOVIE_RELATED_NEWS_SINGLE

        }


    }
}