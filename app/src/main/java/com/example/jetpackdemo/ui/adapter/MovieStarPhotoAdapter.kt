package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ItemMoviestarPhotoBinding

class MovieStarPhotoAdapter : BaseQuickAdapter<String, BaseViewHolder> {
    constructor(list: MutableList<String>) : super(R.layout.item_moviestar_photo, list)


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMoviestarPhotoBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: String) {

        BaseDataBindingHolder<ItemMoviestarPhotoBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }

    }

}