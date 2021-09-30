package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import cn.jzvd.JzvdStd
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.VideoPreview
import com.example.jetpackdemo.databinding.FragmentMovieVideoBinding
import com.example.jetpackdemo.ui.adapter.MyViewPager
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.MovieVideoViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieVideoFragment : BaseFragment<MovieVideoViewModel, FragmentMovieVideoBinding>() {
    private val arrayList = ArrayList<Fragment>()
    private val title = arrayOf("简介", "评论")
    private val jzvdPlayer by lazy { mDatabind.jzvdPlayer }
    override fun createObserver() {

        /**
         *  监听Bundle传递数据变化
         *  @see
         *  @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         *  监测网络请求数据变化
         *  @see MutableLiveData<ResultState<VideoPreview>>
         *
         */
        monitorNetworkVideoPreview()


        /**
         * 监听Intent传递数据变化
         * @see VIDEOPREVIEWBEAN_DATA
         * @see mViewModel.videoPreview_Data
         */
        monitorBundleParamVideoPreviewData()

    }

    private fun monitorBundleParamVideoPreviewData() {

        //
        //        if (mViewModel.videoPreview_Data.value != null) {
        //
        //            //初始化 movieId
        //
        //            mViewModel.movieId.value = mViewModel.movieId.value
        //
        //            /**
        //             *  加载视频
        //             */
        //            loadVideoByUrl(mViewModel.videoPreview_Data.value!!.detailUrl)
        //
        //
        //        } else {
        //            mViewModel.movieId.value.toString()
        //                .loge(javaClass.simpleName + ".bundle.params.VIDEOPREVIEWBEAN_DATA")
        //
        //        }
        mViewModel.videoPreview_Data.observe(this) {

            if (it != null) {
                //初始化 movieId

                mViewModel.movieId.value = it.movieId

                /**
                 * 加载视频
                 */
                loadVideoByUrl(it.detailUrl)
            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.videoPreview_Data")

            }


        }

    }

    private fun monitorNetworkVideoPreview() {

        mViewModel.videoPreview.observe(this) {

            parseState(it, {
                setPreviewVideoList(it)
            }, {
                showErrorMessage(it)
            })
        }

    }

    private fun setPreviewVideoList(videoPreview: VideoPreview) {

        val data = videoPreview.data[0]
        val url = data.url

        /**
         *    加载视频
         */
        loadVideoByUrl(url)

        /**
         *  初始化Fragment
         */
        initFragmentByData(data)


    }

    private fun initFragmentByData(data: VideoPreview.Data) {


        arrayList.clear()


        val movieVideoDescFragment = MovieVideoDescFragment.newInstance(data)
        val movieVideoCommentFragment = MovieVideoCommentFragment.newInstance()

        //将Fragment添加到集合
        arrayList.add(movieVideoDescFragment)
        arrayList.add(movieVideoCommentFragment)

        val viewPagerAdapter = MyViewPager(arrayList, childFragmentManager)   //适配器
        mDatabind.viewPager.adapter = viewPagerAdapter    //给ViewPager添加适配器
        mDatabind.tabLayout.setViewPager(mDatabind.viewPager, title)


    }

    /**
     *
     * @param url String
     */
//TODO
    fun loadVideoByUrl(url: String) {
        jzvdPlayer.setUp(url, "", JzvdStd.SCREEN_NORMAL)
        jzvdPlayer.startVideo()


    }

    private fun monitorBundleParamMovieId() {


        mViewModel.movieId.observe(this) {

            if (it != 0) {

                getVideoUrlByMoveId(it)

            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")

            }


        }

    }

    private fun getVideoUrlByMoveId(movieId: Int) {

        mViewModel.getPreviewVideoList(movieId, mViewModel.limit, mViewModel.offset)

    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_video
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         *
         *  接收Bundle参数
         */
        receiverBundleParam()

    }

    private fun receiverBundleParam() {

        arguments?.let {

            mViewModel.movieId.value = MovieVideoFragmentArgs.fromBundle(it).movieId
            mViewModel.videoPreview_Data.value =
                MovieVideoFragmentArgs.fromBundle(it).videoPreviewData

        }

    }

}