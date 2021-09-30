package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class SubwayParentAdapter : BaseQuickAdapter<Filter.SubItemXXXXX, BaseViewHolder> {
    constructor(mutableList: MutableList<Filter.SubItemXXXXX>) : super(
        R.layout.item_subway_parent,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemXXXXX) {


        //数量
        holder.setText(R.id.tv_count, item.count.toString())
        //地铁线路名称
        holder.setText(R.id.tv_name, item.name)


        /**
         *
         */
        if (holder.adapterPosition == 0) {

            holder.setGone(R.id.tv_count, true)

        }


        /**
         *
         */
        if (item.isSelect) {

            holder.itemView.setBackgroundColor(context.resources.getColor(R.color.white))

        } else {

            holder.itemView.setBackgroundColor(context.resources.getColor(R.color.color_F3F2F2))


        }

    }
}
