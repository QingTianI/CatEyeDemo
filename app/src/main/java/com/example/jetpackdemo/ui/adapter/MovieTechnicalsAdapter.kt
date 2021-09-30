package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieTechnicals
import com.example.jetpackdemo.databinding.ItemMovieTechnicalsBinding

class MovieTechnicalsAdapter : BaseQuickAdapter<MovieTechnicals.Item, BaseViewHolder> {

    constructor(mutableList: MutableList<MovieTechnicals.Item>) : super(
        R.layout.item_movie_technicals,
        mutableList
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieTechnicalsBinding>(viewHolder.itemView)

    }

    override fun convert(holder: BaseViewHolder, item: MovieTechnicals.Item) {

        BaseDataBindingHolder<ItemMovieTechnicalsBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }

    }

}