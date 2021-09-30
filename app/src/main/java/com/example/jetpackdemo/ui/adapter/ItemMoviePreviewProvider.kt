package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.HotMovieList
import com.example.jetpackdemo.databinding.ItemMoviePreviewListBinding

class ItemMoviePreviewProvider : BaseItemProvider<HotMovieList.Hot>() {

    override val itemViewType: Int
        get() = BaseMultiType.TYPE_MOVIE_PREVIEW
    override val layoutId: Int
        get() = R.layout.item_movie_preview_list


    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMoviePreviewListBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: HotMovieList.Hot) {


        BaseDataBindingHolder<ItemMoviePreviewListBinding>(helper.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }

    }


}