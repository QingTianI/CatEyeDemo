package com.example.jetpackdemo.ui.fragment.module.cinema

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.databinding.FragmentMovieVideoDescBinding
import com.example.jetpackdemo.helper.PresenterProxyClick
import com.example.jetpackdemo.ui.adapter.OfficialMovieAdapter
import com.example.jetpackdemo.ui.adapter.PreviewMovieVideoAdapter
import com.example.jetpackdemo.util.*
import com.example.jetpackdemo.viewmodel.fragment.MovieVideoDescViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieVideoDescFragment :
    BaseFragment<MovieVideoDescViewModel, FragmentMovieVideoDescBinding>(), PresenterProxyClick {

    private val previewMovieVideoAdapter by lazy { PreviewMovieVideoAdapter(arrayListOf()) }
    private val officialMovieAdapter by lazy { OfficialMovieAdapter(arrayListOf()) }
    private val tv_preview_movie by lazy { mDatabind.tvPreviewMovie }
    private val tv_official_movie by lazy { mDatabind.tvOfficialMovie }
    private val progressLayoutBasic by lazy { mDatabind.progressLayoutBasic }
    private val progressLayoutVideo by lazy { mDatabind.progressLayoutVideo }

    private val previewMovieVideoList = mutableListOf<VideoPreview.Data>()
    private val officialMovieVideoList = mutableListOf<VideoPreview.Data>()


    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {

                3 -> {
                    val id = msg.arg1

                    /**
                     * ???????????????
                     * @see progressLayoutVideo
                     */
                    showLoading(1)
                    mViewModel.getPreviewVideoList(id, mViewModel.limit, 0)


                }

            }
        }
    }


    override fun createObserver() {

        mDatabind.vm = mViewModel
        mDatabind.presenter = this

        /**
         *  ??????Bundle????????????
         *
         */
        monitorBundleParamvideoPreview_Data()

        /**
         * ????????????????????????
         * @see mViewModel.movieBasicData
         */
        monitorNetworkMovieBasicData()

        /**
         * ????????????????????????
         * @see mViewModel.videoPreviewUnParse
         */
        monitorNetworkVideoPreview()
    }

    private fun monitorNetworkVideoPreview() {

        mViewModel.videoPreview.observe(this) {

            parseState(it, {

                /**
                 *  ???????????????
                 *  @see progressLayoutVideo
                 */
                hideLoading(1)
                /**
                 *  ????????????
                 */
                setVideoPreview(it.data)


            }, {
                showErrorMessage(it)
            })

        }


    }

    private fun setVideoPreview(data: List<VideoPreview.Data>) {

        data[0].isSelect = true


        /**
         *  ??????????????????
         *  @see VideoPreviewBean.data.type
         *  type = 1    ?????????
         *  type = 2    ????????????
         */

        for (item in data) {

            if (item.type == 1) {
                previewMovieVideoList.add(item)


            } else {

                officialMovieVideoList.add(item)
            }


        }


        /**
         *  ????????? previewMovieVideoAdapter??????
         */
        previewMovieVideoAdapter.setList(previewMovieVideoList)
        /**
         *  ??????????????? adapter
         *  @see recyclerView
         *  @see  previewMovieVideoAdapter
         */
        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            previewMovieVideoAdapter,
            mActivity
        )

        /**
         *   item????????????
         */
        previewMovieVideoAdapter.setOnItemClickListener { adapter, view, position ->

            setOnAdapterItemClick(previewMovieVideoAdapter, position)
        }

        setTextDrawableAndColor(clickedTV = tv_preview_movie, noClickedTV = tv_official_movie)

        //        tv_preview_movie.setText("?????????\t${previewMovieVideoList.size}")
        //        tv_official_movie.setText("????????????\t${officialMovieVideoList.size}")

    }

    private fun monitorNetworkMovieBasicData() {

        mViewModel.movieBasicData.observe(this) {

            parseState(it, {

                mViewModel.movieId = it.data.movie.id
                mDatabind.data = it.data.movie

                /**
                 * ???????????????
                 * @see progressLayoutBasic
                 */
                hideLoading(0)

            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun monitorBundleParamvideoPreview_Data() {

        mViewModel.videoPreview_Data.observe(this) {

            if (it != null) {


                /**
                 * ????????????????????????
                 *
                 */
                setMovieBasicByMovieId(it.movieId)

                /**
                 * ??????adapter
                 */
                setMovieTotalVideoByMovieId(it.movieId)


            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.videoPreview_Data")
            }

        }


    }

    private fun setMovieTotalVideoByMovieId(movieId: Int) {


        val message = Message()
        message.arg1 = movieId
        message.what = 3
        handler.sendMessageDelayed(message, 600)

    }

    private fun setMovieBasicByMovieId(movieId: Int) {

        /**
         * ???????????????
         * @see progressLayoutBasic
         */
        showLoading(0)
        mViewModel.getMovieBasicData(movieId)


    }


    override fun layoutId(): Int {

        return R.layout.fragment_movie_video_desc
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.vm = mViewModel
        /**
         * ??????bundle??????
         *
         */
        receiverBundleParam()
    }

    private fun receiverBundleParam() {


        arguments?.let {

            mViewModel.videoPreview_Data.value = it.getParcelable(VIDEOPREVIEW_DATA)
        }

    }


    companion object {

        const val VIDEOPREVIEW_DATA = "videoPreview_Data"
        fun newInstance(data: VideoPreview.Data) =
            MovieVideoDescFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        VIDEOPREVIEW_DATA, data
                    )
                }
            }

    }

    override fun onClick(v: View?) {


        when (v?.id) {

            R.id.lv_hot_movie -> {

                /**
                 *  ??????????????????
                 *  @see MovieDetailFragment
                 */
                toMovieDetailFragment()
            }
            R.id.lv_preview_movie -> {


                toMovieDetailFragment()
            }

            R.id.tv_preview_movie -> {

                setPreviewMovieVideoAdapter()

            }
            R.id.tv_official_movie -> {


                setOfficialMovieAdapter()
            }

        }

    }

    private fun setOfficialMovieAdapter() {


        setTextDrawableAndColor(
            clickedTV = tv_official_movie,
            noClickedTV = tv_preview_movie
        )

        /**
         *      ?????????officialMovieAdapter ??????
         */
        officialMovieAdapter.setList(officialMovieVideoList)
        /**
         *   ???????????????
         *   @see officialMovieAdapter
         *   @see recyclerView
         */
        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            officialMovieAdapter,
            mActivity
        )

        /**
         *  item ??????
         */
        officialMovieAdapter.setOnItemClickListener { adapter, view, position ->


            setOnAdapterItemClick(officialMovieAdapter, position)

        }
    }

    private fun setPreviewMovieVideoAdapter() {

        setTextDrawableAndColor(
            clickedTV = tv_preview_movie,
            noClickedTV = tv_official_movie
        )


        /**
         *  ??????????????? adapter
         *  @see previewMovieVideoAdapter
         *  @see recyclerView
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            previewMovieVideoAdapter,
            mActivity
        )
        /**
         *  item ??????
         */
        previewMovieVideoAdapter.setOnItemClickListener { adapter, view, position ->

            setOnAdapterItemClick(previewMovieVideoAdapter, position)

        }

    }

    /**
     * ????????????
     * @see MovieDetailFragmentv
     */
    private fun toMovieDetailFragment() {

        nav().navigateAction(R.id.action_movieDetailFragment_self,
            mViewModel.movieId?.let { MovieDetailFragmentArgs(it).toBundle() })

    }


    private fun setTextDrawableAndColor(
        clickedTV: TextView,
        noClickedTV: TextView
    ) {

        clickedTV.setTextColor(resources.getColor(R.color.colorPrimary))
        clickedTV.background = resources.getDrawable(R.drawable.shape_tv_movie_tab_clicked)

        noClickedTV.setTextColor(resources.getColor(R.color.color_000))
        noClickedTV.background = resources.getDrawable(R.drawable.shape_tv_movie_tab_un_clicked)


    }

    /**
     *
     * @param adapter BaseQuickAdapter<T, BaseViewHolder>
     * @param currentPosition Int
     * @param mutableList MutableList<Data>
     */
    private fun <T> setOnAdapterItemClick(
        adapter: BaseQuickAdapter<T, BaseViewHolder>,
        currentPosition: Int

    ) {

        /**
         *  ??????
         *  @see currentPosition
         *  @see mutableList
         */
        val mutableList = adapter.data as MutableList<VideoPreview.Data>
        val data = mutableList[currentPosition]

        if (mViewModel.prePosition == currentPosition) {


            /**
             *    ????????????
             */
            setMovieComment(data)

            getFragmentByFragmentManager(mActivity)?.let {

                if (it is MovieVideoFragment) {

                    val movieVideoFragment = it
                    movieVideoFragment.loadVideoByUrl(data.url)

                }

            }

        } else {


            getFragmentByFragmentManager(mActivity)?.let {

                if (it is MovieVideoFragment) {

                    val movieVideoFragment = it
                    movieVideoFragment.loadVideoByUrl(data.url)

                }

            }


            mutableList[currentPosition].isSelect = true


            mutableList[currentPosition].isSelect = true
            mutableList[mViewModel.prePosition].isSelect = false


        }
        //????????????item position?????????
        mViewModel.prePosition = currentPosition

        //   ??????movieVideoPreviewListAdapterI??????
        adapter.notifyDataSetChanged()


    }

    /**
     *   ????????????&??????
     */
    private fun setMovieComment(data: VideoPreview.Data) {

        mViewModel.videoPreview_Data.value = data

    }


    fun showLoading(progressLayoutId: Int) {

        when (progressLayoutId) {

            0 -> {
                if (!progressLayoutBasic.isContent) {

                    progressLayoutBasic.showLoading()
                }
            }
            1 -> {
                if (!progressLayoutVideo.isContent) {

                    progressLayoutVideo.showLoading()
                }
            }


        }

    }

    fun hideLoading(progressLayoutId: Int) {

        when (progressLayoutId) {

            0 -> {
                if (!progressLayoutBasic.isContent) {

                    progressLayoutBasic.showContent()
                }
            }
            1 -> {


                if (!progressLayoutVideo.isContent) {

                    progressLayoutVideo.showContent()
                }
            }


        }


    }
}