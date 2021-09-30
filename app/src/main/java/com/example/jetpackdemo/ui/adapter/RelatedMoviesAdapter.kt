package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.RelatedMovies
import com.example.jetpackdemo.databinding.ItemRelatedMoviesBinding

class RelatedMoviesAdapter : BaseQuickAdapter<RelatedMovies.Item, BaseViewHolder> {



    constructor(mutableList: MutableList<RelatedMovies.Item>) : super(
        R.layout.item_related_movies,
        mutableList
    )



    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemRelatedMoviesBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: RelatedMovies.Item) {


        BaseDataBindingHolder<ItemRelatedMoviesBinding>(holder.itemView).dataBinding?.apply {

           this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()

        }


    }

    inner class Presenter {

        fun sc(sc: Double) = if (sc != 0.0) "${sc}评分" else ""

    }
}