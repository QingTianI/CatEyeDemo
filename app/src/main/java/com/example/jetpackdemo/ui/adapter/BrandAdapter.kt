package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.Filter
import com.example.jetpackdemo.databinding.ItemBrandBinding

class BrandAdapter : BaseQuickAdapter<Filter.SubItem, BaseViewHolder> {

    private var presenter:Presenter

    constructor(list: MutableList<Filter.SubItem>) : super(R.layout.item_brand, list) {

        presenter = Presenter()
    }


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemBrandBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Filter.SubItem) {


        BaseDataBindingHolder<ItemBrandBinding>(holder.itemView).dataBinding?.apply {
            data = item
            presenter = presenter
            executePendingBindings()
        }


        if (holder.adapterPosition == 0) {

            holder.setGone(R.id.tv_count, true)
        }

    }

    inner class Presenter {
        /**
         *
         * @param isSelect Boolean
         * @return Int
         */

        fun tc(isSelect: Boolean): Int {

            return if (isSelect) {
                context.resources.getColor(R.color.colorPrimary)
            } else {
                context.resources.getColor(R.color.color_66666)
            }
        }




    }
}