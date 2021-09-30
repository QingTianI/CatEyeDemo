package com.example.jetpackdemo.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.VideoPreview

class MoviePreViewVideoListAdapter: BaseQuickAdapter<VideoPreview.Data, BaseViewHolder> {

    constructor(mutableList: MutableList<VideoPreview.Data>) : super(
        R.layout.item_preview_movie_list,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: VideoPreview.Data) {


        Glide.with(context).load(item.img).into(holder.getView(R.id.iv_img))


        //视频内容介绍
        holder.setText(R.id.tv_tl,item.tl)


    }
}