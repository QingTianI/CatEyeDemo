package com.example.jetpackdemo.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class HalfTypeServiceAdapter : BaseQuickAdapter<Filter.SubItemXXXX, BaseViewHolder> {

    constructor(mutableList: MutableList<Filter.SubItemXXXX>) : super(
        R.layout.item_half_type_service,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemXXXX) {

        holder.setText(R.id.tv_name, item.name)


        val tv_name = holder.getView<TextView>(R.id.tv_name)

        if (item.isSelect) {
            tv_name.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_reset_press))
            holder.setTextColor(R.id.tv_name, context.resources.getColor(R.color.colorPrimary))

        } else {

            tv_name.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_reset_un_press))
            holder.setTextColor(R.id.tv_name, context.resources.getColor(R.color.color_66666))


        }
    }
}