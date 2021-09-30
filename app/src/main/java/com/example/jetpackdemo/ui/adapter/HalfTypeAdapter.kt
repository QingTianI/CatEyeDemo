package com.example.jetpackdemo.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter

class HalfTypeAdapter: BaseQuickAdapter<Filter.SubItemXXX, BaseViewHolder> {

    constructor(mutableList: MutableList<Filter.SubItemXXX>) : super(
        R.layout.item_half_type,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: Filter.SubItemXXX) {



        holder.setText(R.id.tv_name, item.name)


        val tv_name = holder.getView<TextView>(R.id.tv_name)

        if (item.isSelect) {
            tv_name.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_reset_press))
            holder.setTextColor(R.id.tv_name, context.resources.getColor(R.color.colorPrimary))

        } else {

            tv_name.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_reset_un_press))
            holder.setTextColor(R.id.tv_name, context.resources.getColor(R.color.color_66666))


        }

        /**
         *
         *    2   根据Striing长度设置TextView长度
         */

        val nameLength = item.name.length

        if (nameLength >= 2 && nameLength <=5) {

            tv_name.textSize = 16F
        } else  if (nameLength >=5 && nameLength <= 8){

            tv_name.textSize = 12F

        }else{
            tv_name.textSize = 10F

        }
    }
}