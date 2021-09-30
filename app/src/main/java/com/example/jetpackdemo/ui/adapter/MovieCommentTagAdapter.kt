package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieCommentTag
import com.example.jetpackdemo.databinding.ItemMovieMovieComentTagBinding

class MovieCommentTagAdapter : BaseQuickAdapter<MovieCommentTag.Data, BaseViewHolder> {


    constructor(mutableList: MutableList<MovieCommentTag.Data>) : super(
        R.layout.item_movie_movie_coment_tag,
        mutableList
    )


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieMovieComentTagBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieCommentTag.Data) {

        BaseDataBindingHolder<ItemMovieMovieComentTagBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()

        }


    }

    inner class Presenter {

        fun tvCount(tagName: String, count: Int) = "${tagName}\t${count}"
    }
}