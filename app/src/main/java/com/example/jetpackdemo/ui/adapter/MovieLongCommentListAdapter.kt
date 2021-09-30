package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieLongCommentList
import com.example.jetpackdemo.databinding.ItemMovieLoneCommentListBinding

class MovieLongCommentListAdapter :
    BaseQuickAdapter<MovieLongCommentList.FilmReview, BaseViewHolder> {

    constructor(mutableList: MutableList<MovieLongCommentList.FilmReview>) : super(
        R.layout.item_movie_lone_comment_list,
        mutableList
    )


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieLoneCommentListBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieLongCommentList.FilmReview) {

        BaseDataBindingHolder<ItemMovieLoneCommentListBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }

    }



}