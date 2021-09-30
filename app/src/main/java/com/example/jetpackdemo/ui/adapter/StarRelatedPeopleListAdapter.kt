package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.StarRelatedPeople
import com.example.jetpackdemo.databinding.ItemRelatedPeopleBinding

class StarRelatedPeopleListAdapter : BaseQuickAdapter<StarRelatedPeople.Relation, BaseViewHolder> {
    constructor(mutableList: MutableList<StarRelatedPeople.Relation>) : super(
        R.layout.item_related_people,
        mutableList
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemRelatedPeopleBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: StarRelatedPeople.Relation) {

        BaseDataBindingHolder<ItemRelatedPeopleBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.executePendingBindings()
        }



    }

}