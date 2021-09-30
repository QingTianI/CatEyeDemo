package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieCommentTag

class MovieShortCommentTagAdapter : BaseQuickAdapter<MovieCommentTag.Data, BaseViewHolder> {
    constructor(mutableList: MutableList<MovieCommentTag.Data>) : super(
        R.layout.item_movie_short_comment_tag,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: MovieCommentTag.Data) {

        if (item.isSelected) {

            holder.setBackgroundResource(R.id.tv_tagName, R.drawable.shape_tv_movie_tab_clicked)
            holder.setTextColor(R.id.tv_tagName, context.resources.getColor(R.color.colorPrimary))

        } else {

            holder.setBackgroundResource(R.id.tv_tagName, R.drawable.shape_tv_movie_tab_un_clicked)
            holder.setTextColor(R.id.tv_tagName, context.resources.getColor(R.color.color_000))

        }



        holder.setText(R.id.tv_tagName, "${item.tagName}\t${item.count}")

    }

}