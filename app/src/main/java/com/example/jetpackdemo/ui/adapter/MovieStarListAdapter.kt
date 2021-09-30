package com.example.jetpackdemo.ui.adapter

import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.MovieStar
import com.example.jetpackdemo.databinding.ItemMoviestarlistBinding

class MovieStarListAdapter : BaseQuickAdapter<MovieStar.Data, BaseViewHolder> {

    constructor(mutableList: MutableList<MovieStar.Data>) : super(
        R.layout.item_moviestarlist,
        mutableList
    )


    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMoviestarlistBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MovieStar.Data) {


        BaseDataBindingHolder<ItemMoviestarlistBinding>(holder.itemView).dataBinding?.apply {

            this.data = item
            this.presenter = Presenter(holder.adapterPosition)
            this.executePendingBindings()
        }

    }

    inner class Presenter(val adapterPosition: Int) {


        fun ivStVisibility(still: String, adapterPosition: Int) =
            if (adapterPosition == 0 || TextUtils.isEmpty(still)) {

                View.GONE
            } else {

                View.INVISIBLE

            }


    }
}