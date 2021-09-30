package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class CityAreaParentAdapter : BaseQuickAdapter<Filter.SubItemX, BaseViewHolder> {

    constructor(mutableList: MutableList<Filter.SubItemX>) : super(
        R.layout.item_city_area_parent,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemX) {


        //数量
        holder.setText(R.id.tv_count, item.count.toString())
        //地区名字
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