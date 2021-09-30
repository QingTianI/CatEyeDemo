package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.databinding.FragmentMovieStarVideoBinding
import com.example.jetpackdemo.ui.adapter.MoviePreViewVideoListAdapter
import com.example.jetpackdemo.ui.adapter.OfficialMovieListAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.MovieStarVideoViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieStarVideoFragment :
    BaseFragment<MovieStarVideoViewModel, FragmentMovieStarVideoBinding>(), View.OnClickListener {

    private val previewMovieVideoAdapter by lazy { MoviePreViewVideoListAdapter(arrayListOf()) }
    private val officialMovieListAdapter by lazy { OfficialMovieListAdapter(arrayListOf()) }

    private val tv_official_movie by lazy { mDatabind.tvOfficialMovie }
    private val tv_preview_movie by lazy { mDatabind.tvPreviewMovie }
    override fun createObserver() {


        /**
         *  监听Bundle数据变化
         *  @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         * 监听网络数据变化
         * @see mViewModel.videoPreview
         */
        monitorNetworkVideoPreview()

    }

    private fun monitorNetworkVideoPreview() {

        mViewModel.videoPreview.observe(this) {

            parseState(it,
                {

                    /**
                     *  设置数据
                     */
                    setVideoPreviewBean(it.data)

                }, {

                    showErrorMessage(it)
                })
        }
    }

    private fun setVideoPreviewBean(data: List<VideoPreview.Data>) {


        /**
         *  判断电影类型
         *  @see VideoPreviewBean.data.type
         *  type = 1    预告片
         *  type = 2    官方视屏
         */

        for (item in data) {

            if (item.type == 1) {
                mViewModel.previewMovieVideoList.add(item)


            } else {

                mViewModel.officialMovieVideoList.add(item)
            }

        }


        /**
         *  预加载
         *  @see previewMovieVideoAdapter
         */
        previewLoadMoviePreViewVideoListAdapter()


    }

    private fun previewLoadMoviePreViewVideoListAdapter() {


        previewMovieVideoAdapter.setList(mViewModel.previewMovieVideoList)

        setTextDrawableAndColor(
            clickedTV = tv_preview_movie,
            noClickedTV = tv_official_movie
        )


    }

    private fun monitorBundleParamMovieId() {

        mViewModel.movieId.observe(this) {

            if (it != 0) {

                /**
                 *
                 */
                getPreviewVideoList(it)

            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")


            }

        }


    }

    private fun getPreviewVideoList(movieId: Int) {

        /**
         *  点击事件
         */
        mDatabind.tvPreviewMovie.setOnClickListener(this)
        mDatabind.tvOfficialMovie.setOnClickListener(this)

        /**
         *  加载适配器
         *  @see  mDatabind.recyclerView
         *  @see previewMovieVideoAdapter
         */
        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            previewMovieVideoAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )




        /**
         *  请求网络数据
         */
        mViewModel.getPreviewVideoList(movieId, mViewModel.limit, 0)


        /**
         *   item 点击跳转界面
         *   @see  MovieVideoFragment
         */
        previewMovieVideoAdapter.setOnItemChildClickListener { adapter, view, position ->


//                TODO
//                makeToast(mActivity,"-------------------------")
//                val data = mViewModel.previewMovieVideoList[position]
//
//                nav().navigateAction(R.id.action_to_movieVideoFragment,MovieVideoFragmentArgs(0,data).toBundle())

        }




    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_star_video
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *  接收Bundle数据
         */
        receiverBundleParam()


    }

    private fun receiverBundleParam() {

        arguments?.let {
            mViewModel.movieId.value = it.getInt(MOVIEID)
        }
    }


    companion object {

        const val MOVIEID = "movieId"

        fun newInstance(movieId: Int) =
            MovieStarVideoFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIEID, movieId)
                }
            }
    }

    override fun onClick(v: View?) {


        when (v?.id) {

            R.id.tv_preview_movie -> {

                setTextDrawableAndColor(
                    clickedTV = tv_preview_movie,
                    noClickedTV = tv_official_movie
                )

                /**
                 *  加载适配器 adapter
                 *  @see previewMovieVideoAdapter
                 *  @see recyclerView
                 */

                mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
                    previewMovieVideoAdapter,
                    mActivity,
                    RecyclerView.HORIZONTAL
                )


            }
            else -> {


                setTextDrawableAndColor(
                    clickedTV = tv_official_movie,
                    noClickedTV = tv_preview_movie
                )


                officialMovieListAdapter.setList(mViewModel.officialMovieVideoList)


                /**
                 *   加载适配器 adapter
                 *   @see officialMovieListAdapter
                 *   @see recyclerView
                 */
                mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
                    officialMovieListAdapter,
                    mActivity,
                    RecyclerView.HORIZONTAL
                )


            }


        }


    }

    private fun setTextDrawableAndColor(
        clickedTV: TextView,
        noClickedTV: TextView
    ) {

        clickedTV.setTextColor(resources.getColor(R.color.white))
        clickedTV.background = resources.getDrawable(R.drawable.shape_button_pressed)

        noClickedTV.setTextColor(resources.getColor(R.color.color_EEEDED))
        noClickedTV.background = resources.getDrawable(R.drawable.shape_button_unpressed)


    }

}