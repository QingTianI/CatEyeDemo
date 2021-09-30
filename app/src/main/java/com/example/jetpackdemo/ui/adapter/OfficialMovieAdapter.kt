package com.example.jetpackdemo.ui.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.databinding.ItemOfficialMovieVideoBinding

class OfficialMovieAdapter : BaseQuickAdapter<VideoPreview.Data, BaseViewHolder> {
    constructor(list: MutableList<VideoPreview.Data>) : super(
        R.layout.item_official_movie_video,
        list

    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemOfficialMovieVideoBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: VideoPreview.Data) {


        BaseDataBindingHolder<ItemOfficialMovieVideoBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()
        }

    }

    inner class Presenter {

        /**
         *
         * @param isSelect Boolean
         * @return Int
         */
        fun selectedColor(isSelect: Boolean) = if (isSelect) {
            context.resources.getColor(R.color.colorPrimary)
        } else {

            context.resources.getColor(R.color.color_grey)

        }


        fun isVisibility(isSelect: Boolean) = if (isSelect) {

            View.VISIBLE
        } else {

            View.GONE
        }
    }

}