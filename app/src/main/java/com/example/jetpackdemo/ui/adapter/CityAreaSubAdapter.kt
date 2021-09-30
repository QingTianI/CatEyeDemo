package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class CityAreaSubAdapter : BaseQuickAdapter<Filter.SubItemXX, BaseViewHolder> {

    constructor(mutableList: MutableList<Filter.SubItemXX>) : super(
        R.layout.item_city_area_sub,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemXX) {


        if (holder.adapterPosition == 0) {

            holder.setGone(R.id.tv_count, true)

        }

        //数量
        holder.setText(R.id.tv_count, item.count.toString())
        //地区名字
        holder.setText(R.id.tv_name, item.name)
    }

}