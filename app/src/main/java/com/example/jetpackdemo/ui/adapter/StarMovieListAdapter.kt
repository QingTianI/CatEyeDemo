package com.example.jetpackdemo.ui.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.StarMovies
import com.example.jetpackdemo.databinding.ItemStarMovieBinding

class StarMovieListAdapter : BaseQuickAdapter<StarMovies.Movy, BaseViewHolder>, LoadMoreModule {

    constructor(mutableList: MutableList<StarMovies.Movy>) : super(
        R.layout.item_star_movie,
        mutableList
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemStarMovieBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: StarMovies.Movy) {

        BaseDataBindingHolder<ItemStarMovieBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.presenter  = Presenter()
            this.executePendingBindings()
        }

    }

    inner class Presenter {

        fun cvVisibility(still: String) = if (still.isEmpty()) View.GONE else View.VISIBLE

    }
}