package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.HotMovieList

class HotShowAdapter : BaseProviderMultiAdapter<HotMovieList.Hot> {

    constructor(mutableList: MutableList<HotMovieList.Hot>) : super(mutableList) {

        /**
         *  添加itemProvider
         */
        addItemProvider(ItemMovieHotProvider())
        addItemProvider(ItemMoviePreviewProvider())

        /**
         * 添加响应事件的viewid
         */
        addChildClickViewIds(
            R.id.fl_hot_movie,
            R.id.ll_movie_detail,
            R.id.btn_buy,
            R.id.fl_hot_movie_preview,
            R.id.ll_movie_detail_preview,
            R.id.btn_buy_preview
        )
    }

    override fun getItemType(data: List<HotMovieList.Hot>, position: Int): Int {

        val hot = data[position]

        return if (hot.globalReleased || hot.preShow) {
            BaseMultiType.TYPE_MOVIE_HOT
        } else {

            BaseMultiType.TYPE_MOVIE_PREVIEW
        }


    }
}