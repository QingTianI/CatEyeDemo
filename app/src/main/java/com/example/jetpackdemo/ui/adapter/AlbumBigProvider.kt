package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Album
import com.example.jetpackdemo.databinding.ItemMovieStarAlbumBigBinding

class AlbumBigProvider:BaseItemProvider<Album>() {
    override val itemViewType: Int
        get() = BaseMultiType.TYPE_ALBUM_BIG


    override val layoutId: Int
        get() =  R.layout.item_movie_star_album_big

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieStarAlbumBigBinding>(viewHolder.itemView)
    }
    override fun convert(helper: BaseViewHolder, item: Album) {

        BaseDataBindingHolder<ItemMovieStarAlbumBigBinding>(helper.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }
    }
}