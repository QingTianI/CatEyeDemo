package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Album

class MovieStarAlbumBigListAdapter : BaseDelegateMultiAdapter<Album, BaseViewHolder> {

    constructor(mutableList: MutableList<Album>) : super(mutableList) {

//        第一步
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Album>() {
            override fun getItemType(data: List<Album>, position: Int): Int {

                if (position % 2 == 0) {

                    return BaseMultiType.TYPE_ALBUM_BIG

                } else {
                    return BaseMultiType.TYPE_ALBUM_SMALL

                }

            }
        })


//       // 第二部，绑定 item 类型
        getMultiTypeDelegate()?.apply {

            addItemType(BaseMultiType.TYPE_ALBUM_BIG, R.layout.item_movie_star_album_big)
            addItemType(BaseMultiType.TYPE_ALBUM_SMALL, R.layout.layout_movie_star_album_small)
        }


    }

    override fun convert(holder: BaseViewHolder, item: Album) {


//        if (holder.itemViewType == BaseMultiType.TYPE_ALBUM_BIG) {
//
//
//            val imageUrl = UrlUtils.resetPicUrl(item.bigPhoto)
//
//            Glide.with(context).load(imageUrl).into(holder.getView(R.id.iv_avatar))
//
//        } else {

//
//
//         /**
//         * 加载适配器
//         * @see recyclerView_star_movie_album_small
//         * @see movieStarAlbumListAdapter
//         */
//            val movieStarAlbumSmallListAdapter = MovieStarAlbumSmallListAdapter(item.smallPhoto)
//            val recyclerView_star_movie_album_small = holder.getView<RecyclerView>(R.id.recyclerView_star_movie_album_small)
//
//            recyclerView_star_movie_album_small.loadRecyclerAdapterByLinearLayoutManager(
//            movieStarAlbumSmallListAdapter,
//            context
//        )


//        }

    }

}