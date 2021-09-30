package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.data.model.bean.Album

class MovieStarAlbumBigListAdapterI : BaseProviderMultiAdapter<Album> {
    constructor(mutableList: MutableList<Album>) : super(mutableList) {

        addItemProvider(AlbumBigProvider())
        addItemProvider(AlbumSmallProvider())

    }

    override fun getItemType(data: List<Album>, position: Int): Int {

        if (position % 2 == 0) {

            return BaseMultiType.TYPE_ALBUM_BIG

        } else {
            return BaseMultiType.TYPE_ALBUM_SMALL

        }
    }
}