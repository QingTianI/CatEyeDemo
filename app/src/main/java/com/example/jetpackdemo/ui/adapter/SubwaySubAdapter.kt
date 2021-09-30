package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class SubwaySubAdapter : BaseQuickAdapter<Filter.SubItemXXXXXX, BaseViewHolder> {
    constructor(mutableList: MutableList<Filter.SubItemXXXXXX>) : super(
        R.layout.item_subway_sub,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemXXXXXX) {


        if (holder.adapterPosition == 0) {

            holder.setGone(R.id.tv_count, true)

        }

        //数量
        holder.setText(R.id.tv_count, item.count.toString())
        //区域名字
        holder.setText(R.id.tv_name, item.name)


    }


}
