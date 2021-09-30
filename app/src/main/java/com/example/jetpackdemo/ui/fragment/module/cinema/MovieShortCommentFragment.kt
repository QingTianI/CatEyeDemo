package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.MovieShortCommentDetailedAdapter
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.MovieCommentTag
import com.example.jetpackdemo.data.model.bean.MovieCommentTagDetailed
import com.example.jetpackdemo.databinding.FragmentMovieShortCommentBinding
import com.example.jetpackdemo.ui.adapter.MovieShortCommentTagAdapter
import com.example.jetpackdemo.util.*
import com.example.jetpackdemo.viewmodel.fragment.MovieShortCommentViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieShortCommentFragment :
    BaseFragment<MovieShortCommentViewModel, FragmentMovieShortCommentBinding>() {


    private val movieShortCommentDetailedAdapter by lazy {
        MovieShortCommentDetailedAdapter(
            arrayListOf()
        )
    }
    private val loadMoreModule by lazy { movieShortCommentDetailedAdapter.loadMoreModule }


    override fun createObserver() {


        /**
         *  监听Bundle传输数据变化
         *  @see mViewModel.movieId
         */
        mViewModel.monitorMovieIdBoolean = monitorBundleParamMovieIdObserve()

        /**
         * 监听Bundle传输数据变化
         * @see mViewModel.tag
         */
        mViewModel.monitorTagBoolean = monitorBundleParamTag()

        /**
         *  监听Bundle传输数据变化
         * @see mViewModel.movieCommentTag_Data_List
         */
        monitorBundleParamMovieCommentTag_Data_List()


        /**
         *  监听Bundle传输数据变化
         *  @see mViewModel.movieName
         */

        monitorBundleParamMovieName()


        /**
         * 监听网络请求数据变化
         * @see mViewModel.movieCommentTagDetailed
         */

        monitorNetworkMovieCommentTagDetailed()


        /**
         * 监听网格请求数据
         * @see mViewModel.moreMovieCommentTagDetailed
         *
         */

        monitorNetworkMoreMovieCommentTagDetailed()

    }

    private fun monitorNetworkMoreMovieCommentTagDetailed() {


        mViewModel.moreMovieCommentTagDetailed.observe(this) {

            parseState(it, {

                movieShortCommentDetailedAdapter.addData(it.data.cmts)
                loadMoreModule.loadMoreComplete()

                /**
                 *  判断是否有更多数据
                 */
                if (it.paging.hasMore == false) {

                    loadMoreModule.loadMoreEnd()


                }

            }, {

                showErrorMessage(it)
            })
        }

    }

    private fun monitorNetworkMovieCommentTagDetailed() {

        mViewModel.movieCommentTagDetailed.observe(this) {

            parseState(it, {

                /**
                 *  关闭加载框
                 */
                hideLoading()

                movieShortCommentDetailedAdapter.setList(it.data.cmts)


                /**
                 *  总评论数量
                 */
                setCommentTotalCount(it.paging)


            }, {

                showErrorMessage(it)
            })

        }


    }

    private fun setCommentTotalCount(paging: MovieCommentTagDetailed.Paging) {

        mDatabind.paging = paging

    }

    private fun hideLoading() {

        mDatabind.progressLayout.showContent()
    }

    private fun monitorBundleParamMovieName() {


        if (!mViewModel.movieName.value.isEmpty()) {


            /**
             *  初始化title
             */
            initTitle()


        } else {
            mViewModel.movieId.value.toString()
                .loge(javaClass.simpleName + ".bundle.params.movieName")


        }
    }

    private fun initTitle() {


        mDatabind.shortCommentTitle.toolbar.setShortCommentTitle(
            this,
            "猫眼短评",
            mViewModel.movieName.value,
            defaultBackgroundColor = resources.getColor(R.color.colorWhite),

            )


    }

    private fun monitorBundleParamMovieCommentTag_Data_List() {


        if (mViewModel.movieCommentTag_Data_List.value?.isEmpty() == true) {

            mViewModel.movieId.value.toString()
                .loge(javaClass.simpleName + ".bundle.params.movieCommentTag_Data_List")


        } else {


            /**
             *  设置电影短评评论标签
             */
            mViewModel.movieCommentTag_Data_List.value?.let { setMovieCommentTag(it) }


        }


    }

    private fun setMovieCommentTag(list: List<MovieCommentTag.Data>) {
        val movieShortCommentTagAdapter =
            MovieShortCommentTagAdapter(list as MutableList<MovieCommentTag.Data>)


        /**
         *  加载适配器 adapter
         *  @see recyclerView_movie_comment_tag
         *  @see ovieShortCommentTagAdapter
         */


        mDatabind.recyclerViewMovieCommentTag.loadRecyclerAdapterByGridLayoutManager(
            movieShortCommentTagAdapter,
            mActivity,
            3
        )


        /**
         *   item点击事件
         */
        movieShortCommentTagAdapter.setOnItemClickListener { adapter, view, currentPosition ->

            val mutableList = adapter.data as MutableList<MovieCommentTag.Data>
            val item = mutableList[currentPosition]



            if (mViewModel.prePosition == currentPosition) {

                mutableList[currentPosition].isSelected = true
            } else {

                //当前item currentPosition位置 和 上一次点击位置prePosition  不一样，
                // 则将集合mutableList所有对象 isSelected = false,且mutableList#currentPosition = true
                for (item in mutableList) {
                    item.isSelected = false
                }
                mutableList[currentPosition].isSelected = true


            }


            //记录当前的选中currentPosition
            mViewModel.prePosition = currentPosition

            //通知刷新 movieShortCommentTagAdapter
            movieShortCommentTagAdapter.notifyDataSetChanged()


            // 每次点击点击offset 置0
            mViewModel.offset = 0


            if (mViewModel.monitorMovieIdBoolean && mViewModel.monitorTagBoolean) {

                /**
                 *电影短评评论详情
                 */
                setMovieShortCommentDetails(mViewModel.movieId.value, item.tag)


            }


        }

    }

    private fun monitorBundleParamTag(): Boolean {

        if (mViewModel.tag.value != 0) {

            if (mViewModel.monitorMovieIdBoolean) {


                /**
                 *电影短评评论详情
                 */
                setMovieShortCommentDetails(
                    mViewModel.movieId.value,
                    mViewModel.tag.value
                )


            }


            return true
        } else {


            mViewModel.movieId.value.toString()
                .loge(javaClass.simpleName + ".Intent.params.TAG")


            return false
        }


    }

    private fun setMovieShortCommentDetails(movieId: Int, tag: Int) {
        /**
         *  显示加载框
         */
        showLoading()


        /**
         * 加载适配器 adapter
         * @see recyclerView_movie_short_comment_details
         * @see movieShortCommentDetailedAdapter
         */

        mDatabind.recyclerViewMovieShortCommentDetails.loadRecyclerAdapterByLinearLayoutManager(
            movieShortCommentDetailedAdapter,
            mActivity
        )


        /**
         *  请求网络数据
         */
        mViewModel.getMovieCommentShortDetailed(
            movieId,
            tag,
            mViewModel.limit,
            mViewModel.offset
        )


        /**
         *  加载更多数据
         *  @see offset
         *  @see movieShortCommentDetailedAdapter
         */
        loadMoreModule.setOnLoadMoreListener {

            mViewModel.offset += 10
            mViewModel.getMoreMovieCommentShortDetailed(
                movieId,
                tag,
                mViewModel.limit,
                mViewModel.offset
            )


        }


    }

    private fun monitorBundleParamMovieIdObserve(): Boolean {

        if (mViewModel.movieId.value != 0) {

            return true
        } else {


            mViewModel.movieId.value.toString()
                .loge(javaClass.simpleName + ".bundle.params.movieId")

            return false

        }


    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_short_comment
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *  接收Bundle参数
         */
        receiverBundleParam()
    }

    private fun receiverBundleParam() {

        arguments?.let {

            mViewModel.movieName.value = MovieShortCommentFragmentArgs.fromBundle(it).movieName
            mViewModel.movieCommentTag_Data_List.value =
                MovieShortCommentFragmentArgs.fromBundle(it).movieCommentTagData.toList()
            mViewModel.tag.value = MovieShortCommentFragmentArgs.fromBundle(it).tag
            mViewModel.movieId.value = MovieShortCommentFragmentArgs.fromBundle(it).movieId

        }

    }

    fun showLoading() {


        mDatabind.progressLayout.showLoading()


    }

}