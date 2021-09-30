package com.example.jetpackdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R

class DistanceAdapter: BaseQuickAdapter<MutableMap<String, String>, BaseViewHolder> {


    constructor(mutableList: MutableList<MutableMap<String, String>>) : super(
        R.layout.item_diatance,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: MutableMap<String, String>) {

        var key = ""

        for ((k, v) in item) {

            key = k

        }

        holder.setText(R.id.tv_name,key)

        val color = context.resources.getColor(R.color.colorPrimary)

        if (holder.adapterPosition == 0) {

            holder.setTextColor(R.id.tv_name, color)
        }
    }

}