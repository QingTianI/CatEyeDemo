package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieRelatedCompanies
import com.example.jetpackdemo.databinding.ItemMovieProductBinding

class RelatedCompanyAdapter : BaseQuickAdapter<MovieRelatedCompanies.Item, BaseViewHolder> {
    constructor(mutableList: MutableList<MovieRelatedCompanies.Item>) : super(
        R.layout.item_movie_product,
        mutableList
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieProductBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieRelatedCompanies.Item) {
        BaseDataBindingHolder<ItemMovieProductBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }

    }

}