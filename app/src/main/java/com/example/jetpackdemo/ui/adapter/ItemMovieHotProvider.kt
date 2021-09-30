package com.example.jetpackdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.BaseMultiType
import com.example.jetpackdemo.R
import com.example.jetpackdemo.data.model.bean.HotMovieList
import com.example.jetpackdemo.databinding.ItemHotMovieBinding

class ItemMovieHotProvider : BaseItemProvider<HotMovieList.Hot>() {


    override val itemViewType: Int
        get() = BaseMultiType.TYPE_MOVIE_HOT
    override val layoutId: Int
        get() = R.layout.item_hot_movie

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemHotMovieBinding>(viewHolder.itemView)

    }

    override fun convert(helper: BaseViewHolder, item: HotMovieList.Hot) {

        BaseDataBindingHolder<ItemHotMovieBinding>(helper.itemView).dataBinding?.apply {
            this.data = item
            this.presenter = Presenter()
            this.executePendingBindings()
        }

    }

    inner class Presenter {

        fun is2Dor3D(ver: String) = if (ver.contains("IMAX 3D")) {
            context.resources.getDrawable(R.drawable.ic_3d_imax)
        } else {
            context.resources.getDrawable(R.drawable.ic_2d_imax)
        }

    }

}