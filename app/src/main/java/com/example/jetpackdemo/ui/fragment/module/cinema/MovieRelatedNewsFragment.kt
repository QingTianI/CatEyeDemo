package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.MovieRelatedNews
import com.example.jetpackdemo.databinding.FragmentMovieRelatedNewsBinding
import com.example.jetpackdemo.ui.adapter.MovieRelatedNewsAdapterI
import com.example.jetpackdemo.util.*
import com.example.jetpackdemo.viewmodel.fragment.MovieRelatedNewsViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieRelatedNewsFragment :
    BaseFragment<MovieRelatedNewsViewModel, FragmentMovieRelatedNewsBinding>() {

    private val  movieRelatedNewsAdapterI by lazy { MovieRelatedNewsAdapterI(arrayListOf()) }


    override fun createObserver() {


        /**
         * 监听Bundle传输数据变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         *  监听Intent传输数据变化
         * @see mViewModel.movieName
         */
        monitorBundleParamMovieName()


        /**
         * 监测网络请求数据变化
         * @see mViewModel.movieRelatedNewsBeanObserve
         */
        monitorNetworkMovieRelatedNews()

    }

    private fun monitorNetworkMovieRelatedNews() {


        mViewModel.movieRelatedNews.observe(this) {

            parseState(it, {


                movieRelatedNewsAdapterI.setList(it.data.newsList)
            }, {
                showErrorMessage(it)
            })

        }

    }

    private fun monitorBundleParamMovieName() {


        mViewModel.movieName.observe(this) {

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

    }

    private fun initTitle() {

        mDatabind.normalTitle.toolbar.setNormalTitle(
            this,
            mViewModel.movieName.value
        )


    }

    private fun monitorBundleParamMovieId() {

        mViewModel.movieId.observe(this) {
            if (it != 0) {

                /**
                 *  电影相关资讯
                 */
                setMovieRelatedNewsAdapter()


            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")


            }
        }


    }

    private fun setMovieRelatedNewsAdapter() {

        /**
         * 加载适配器 adapter
         * @see   recyclerView
         * @see  movieRelatedNewsAdapter
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieRelatedNewsAdapterI,
            mActivity
        )

        /**
         *  请求网络数据
         */
        mViewModel.getMovieRelatedNews(
            mViewModel.movieId.value,
            mViewModel.limit,
            mViewModel.offset
        )


        /**
         * item 点击事件
         */
        movieRelatedNewsAdapterI.setOnItemChildClickListener { adapter, view, currentPosition ->

            val mutableList = adapter.data as MutableList<MovieRelatedNews.News>
            val item = mutableList[currentPosition]


            val webViewUrl = UrlUtils.getRealWebViewUrl(item.url)


//            WebViewNoTitleActivity.startAction(this, webViewUrl)
            /**
             * 跳转界面
             * @see WebViewNoTitleFragment
            //             */
//            nav().navigateAction(
//                R.id.action_movieRelatedNewsFragment_to_webViewNoTitleFragment,
//                WebViewNoTitleFragmentArgs(webViewUrl).toBundle()
//            )


        }


    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_related_news
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *  接收Bundle参数
         */

        receiverBundleParam()
    }

    private fun receiverBundleParam() {

        arguments?.let {

            mViewModel.movieId.value = MovieRelatedNewsFragmentArgs.fromBundle(it).movieId
            mViewModel.movieName.value = MovieRelatedNewsFragmentArgs.fromBundle(it).movieName
        }
    }
}