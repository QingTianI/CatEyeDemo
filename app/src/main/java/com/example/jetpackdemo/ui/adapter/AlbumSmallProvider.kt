package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Album

class AlbumSmallProvider:BaseItemProvider<Album>(){
    override val itemViewType: Int
        get() = BaseMultiType.TYPE_ALBUM_SMALL

    override val layoutId: Int
        get() = R.layout.layout_movie_star_album_small

    override fun convert(helper: BaseViewHolder, item: Album) {
    }
}