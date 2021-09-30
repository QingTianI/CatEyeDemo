package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieResource
import com.example.jetpackdemo.databinding.ItemMovieResourceBinding

class MovieResourceAdapter : BaseQuickAdapter<MovieResource.Data, BaseViewHolder> {

    constructor(mutableList: MutableList<MovieResource.Data>) : super(
        R.layout.item_movie_resource,
        mutableList
    )



    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieResourceBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieResource.Data) {


        BaseDataBindingHolder<ItemMovieResourceBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()

        }



        if ((holder.adapterPosition % 2) == 1) {

            holder.setGone(R.id.iv_divier, true)
        }


    }

}