package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieLongComment
import com.example.jetpackdemo.databinding.ItemMovieLoneCommentBinding

class MovieLongCommentAdapter : BaseQuickAdapter<MovieLongComment.FilmReview, BaseViewHolder> {

    constructor(mutableList: MutableList<MovieLongComment.FilmReview>) : super(
        R.layout.item_movie_lone_comment,
        mutableList
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieLoneCommentBinding>(viewHolder.itemView)
    }
    override fun convert(holder: BaseViewHolder, item: MovieLongComment.FilmReview) {



        BaseDataBindingHolder<ItemMovieLoneCommentBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }


    }

}
