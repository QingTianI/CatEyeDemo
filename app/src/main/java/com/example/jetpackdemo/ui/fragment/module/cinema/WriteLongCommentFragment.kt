package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import android.view.View
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentWriteLongCommentBinding
import com.example.jetpackdemo.util.makeToast
import com.example.jetpackdemo.util.setLoneLommentTitle
import com.example.jetpackdemo.viewmodel.fragment.WriteLongCommentViewModel

class WriteLongCommentFragment : BaseFragment<WriteLongCommentViewModel,FragmentWriteLongCommentBinding>() {


    override fun createObserver() {


    }

    override fun layoutId(): Int {
        return R.layout.fragment_write_long_comment
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         *  初始化title
         */
        initTitle()

        initRadioGroup()

        initReleaseMessage()
    }

    private fun initTitle() {

        mDatabind.loneCommentTitle.toolbar.setLoneLommentTitle(this,"写长评")

        mDatabind.loneCommentTitle.tvRelease.setOnClickListener {

//        验证标题栏字数

            val title = mDatabind.etInputTitle.text.toString()

            if (title.length < 4) {

                makeToast(mActivity, "标题最少要求有四个哦")
                return@setOnClickListener
            }

            val comment = mDatabind.etInputComment.text.toString()


            if (comment.length < 3000) {

                makeToast(mActivity, "长评内容最少要求有300个哦")

                return@setOnClickListener
            }


        }
    }

    private fun initReleaseMessage() {


//        mDatabind.tv.setOnClickListener {
//
////        验证标题栏字数
//
//            val title = et_input_title.text.toString()
//
//            if (title.length < 4){
//
//                ArmsUtils.makeText(this,"标题最少要求有四个哦")
//                return@setOnClickListener
//            }
//
//            val comment = et_input_comment.text.toString()
//
//
//            if (comment.length < 3000){
//
//                ArmsUtils.makeText(this,"长评内容最少要求有300个哦")
//                return@setOnClickListener
//            }
//
//
//            /**
//             *
//             *   验证成功
//             */
//
//        }

    }

    private fun initRadioGroup() {
        mDatabind.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (group.checkedRadioButtonId) {

                R.id.rb_level_1 -> {
                    mDatabind.rlScoreLevel1.visibility = View.VISIBLE
                    mDatabind.radioGroup.visibility = View.GONE
                }
                R.id.rb_level_2 -> {
                    mDatabind.rlScoreLevel2.visibility = View.VISIBLE
                    mDatabind.radioGroup.visibility = View.GONE

                }
                R.id.rb_level_3 -> {
                    mDatabind.rlScoreLevel3.visibility = View.VISIBLE
                    mDatabind.radioGroup.visibility = View.GONE

                }
                R.id.rb_level_4 -> {
                    mDatabind.rlScoreLevel4.visibility = View.VISIBLE
                    mDatabind.radioGroup.visibility = View.GONE

                }
                else -> {
                    mDatabind.rlScoreLevel5.visibility = View.VISIBLE
                    mDatabind.radioGroup.visibility = View.GONE

                }

            }
        }



    }


}