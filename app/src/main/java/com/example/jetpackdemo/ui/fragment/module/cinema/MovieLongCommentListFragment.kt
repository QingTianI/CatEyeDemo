package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieLongCommentListBinding
import com.example.jetpackdemo.ui.adapter.MovieLongCommentListAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.setNormalTitle
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.MovieLongCommentListViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieLongCommentListFragment :
    BaseFragment<MovieLongCommentListViewModel, FragmentMovieLongCommentListBinding>() {

    private val movieLongCommentListAdapter by lazy { MovieLongCommentListAdapter(arrayListOf()) }


    override fun createObserver() {

        /**
         * 监测Bundle传递数据变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()

        /**
         * 监测网络请求数据变化
         * @see mViewModel.movieLongCommentList
         */
        monitorNetworkMovieLongCommentList()


    }

    private fun monitorNetworkMovieLongCommentList() {
        mViewModel.movieLongCommentList.observe(this) {

            parseState(it,
                {

                    movieLongCommentListAdapter.setList(it.data.filmReviews)
                }, {
                    showErrorMessage(it)
                })
        }

    }

    private fun monitorBundleParamMovieId() {

        mViewModel.movieId.observe(this) {
            if (it != 0) {

                /**
                 *     全部热门长评
                 */
                setMovieLongCommentList(it)

            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")

            }

        }

    }

    private fun setMovieLongCommentList(movieId: Int) {
        /**
         *  加载适配器 adapter
         *  @see recyclerView
         *  @see movieLongCommentListAdapter
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieLongCommentListAdapter,
            mActivity
        )


        /**
         *   强求网络数据
         */
        mViewModel.getMovieLongCommentList(movieId, mViewModel.limit, mViewModel.offset)


    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_long_comment_list
    }

    override fun initView(savedInstanceState: Bundle?) {


        /**
         * 初始化title
         */
        initTitle()

        /**
         * 接收Bundle参数
         */
        receiverBundleParam()
    }

    private fun initTitle() {

        mDatabind.normalTitle.toolbar.setNormalTitle(this, "写长评")


    }

    private fun receiverBundleParam() {


        arguments?.let {

            mViewModel.movieId.value = MovieLongCommentListFragmentArgs.fromBundle(it).movieId
        }
    }

}