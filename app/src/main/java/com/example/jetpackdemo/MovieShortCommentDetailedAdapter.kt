package com.example.jetpackdemo

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.data.model.bean.MovieCommentTagDetailed
import com.example.jetpackdemo.databinding.ItemMovieCommentDetailsBinding
import com.example.jetpackdemo.databinding.LayoutCommentReplyBinding
import com.example.jetpackdemo.util.makeToast
import me.hgj.jetpackmvvm.ext.util.loge

class MovieShortCommentDetailedAdapter :
    BaseQuickAdapter<MovieCommentTagDetailed.Cmt, BaseViewHolder>, LoadMoreModule {

    private var flag = false
    private var dialogFlag = false
    private lateinit var dialog: AlertDialog

    private lateinit var presenter: Presenter

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {

        DataBindingUtil.bind<ItemMovieCommentDetailsBinding>(viewHolder.itemView)
    }

    constructor(mutableList: MutableList<MovieCommentTagDetailed.Cmt>) : super(
        R.layout.item_movie_comment_details,
        mutableList
    )

    override fun convert(holder: BaseViewHolder, item: MovieCommentTagDetailed.Cmt) {

        presenter = Presenter(item)

        BaseDataBindingHolder<ItemMovieCommentDetailsBinding>(holder.itemView).dataBinding?.apply {
            this.data = item
            this.isShow = flag
            this.presenter = this@MovieShortCommentDetailedAdapter.presenter
            this.executePendingBindings()
        }


    }

    inner class Presenter {

        constructor()

        constructor(item: MovieCommentTagDetailed.Cmt) {

            this.item = item

        }

        private lateinit var item: MovieCommentTagDetailed.Cmt


        fun onClick(view: View) {

            when (view.id) {

                R.id.fl -> flag = !flag
                R.id.lv_reply -> createReplyDialog()
                R.id.iv_cancel -> makeToast(context,"-----------------")

            }


        }

        private fun createReplyDialog() {


            val view =
                LayoutInflater.from(context).inflate(R.layout.layout_comment_reply, null, false)

            dialog = AlertDialog.Builder(context).setView(view).create()


            //?????????view
            initDialogView(item)

            //??????dialog??????
            loadDialog(dialog)


        }

        private fun loadDialog(dialog: AlertDialog) {
            //        dialog.setCancelable(false)
            dialog.show()
            val window: Window = dialog.window!!
            val attributes = window.attributes
            attributes.width = LinearLayout.LayoutParams.MATCH_PARENT
            attributes.height = LinearLayout.LayoutParams.MATCH_PARENT
            window.attributes = attributes

            window.setBackgroundDrawableResource(R.color.transparent)
            window.setGravity(Gravity.BOTTOM)


        }

        private fun initDialogView(
            item: MovieCommentTagDetailed.Cmt
        ) {

            val dataBinding = DataBindingUtil.inflate<LayoutCommentReplyBinding>(
                LayoutInflater.from(context),
                R.layout.layout_comment_reply,
                null,
                false
            )

            "$item".loge("MovieCommentTagDetailed.Cmt")

            dataBinding.apply {

                this.data = item
                this.isShow = dialogFlag
                this.presenter = this@MovieShortCommentDetailedAdapter.presenter
                this.executePendingBindings()
            }
        }

        /**
         *
         * @param reply Int
         * @return String
         */
        fun replyCount(reply: Int) = if (reply == 0) {

            "??????"
        } else {

            "${reply}\t??????"
        }

        /**
         *
         * @param approve Int
         * @return String
         */
        fun firstApprove(approve: Int) = if (approve == 0) {
            "?????????"

        } else {
            "$approve"

        }

        /**
         *
         * @param approve Int
         * @return String
         */
        fun showClickApprove(approve: Int) = "${approve + 1}"


        fun setCreatedTime(created: Long): String {

            var time = ""

            val currentTimeMillis = System.currentTimeMillis()  //????????????     ?????? ??????

            val pastTime = currentTimeMillis - created       //????????????   ?????????
            val millisecond = 1000 * 60    // ?????????????????????    ????????????       ??????

            val intervalTime = pastTime / millisecond     //????????????

            if (intervalTime < 60) {


                time = "${intervalTime}?????????"


            } else if (intervalTime >= 60 && intervalTime < 60 * 24) {


                time = "${intervalTime / 60}?????????"


            } else {


                time = "${intervalTime / 60 / 24}??????"

            }

            return time
        }

        /**
         *
         * @param isShow Boolean
         * @return Int
         */
        fun showClickVisibility(isShow: Boolean) = if (isShow) View.VISIBLE else View.GONE

        /**
         *
         * @param isShow Boolean
         * @return Int
         */
        fun showUnClickVisibility(isShow: Boolean) = if (isShow) View.GONE else View.VISIBLE
    }


}