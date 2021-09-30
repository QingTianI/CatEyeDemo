package com.example.jetpackdemo.ui.fragment.module.cinema

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JZUtils
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.*
import com.example.jetpackdemo.databinding.FragmentMovieStarInfoBeanBinding
import com.example.jetpackdemo.helper.PresenterProxyClick
import com.example.jetpackdemo.ui.adapter.MovieStarAlbumBigListAdapter
import com.example.jetpackdemo.ui.adapter.StarMovieListAdapter
import com.example.jetpackdemo.ui.adapter.StarRelatedPeopleListAdapter
import com.example.jetpackdemo.util.*
import com.example.jetpackdemo.viewmodel.fragment.MovieStarInfoBeanViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieStarInfoBeanFragment :
    BaseFragment<MovieStarInfoBeanViewModel, FragmentMovieStarInfoBeanBinding>(),
    PresenterProxyClick {


    private val movieStarAlbumBigListAdapter by lazy { MovieStarAlbumBigListAdapter(arrayListOf()) }
    private val starMovieListAdapter by lazy { StarMovieListAdapter(arrayListOf()) }
    private val loadMoreModule by lazy { starMovieListAdapter.loadMoreModule }
    private val starRelatedPeopleListAdapter by lazy { StarRelatedPeopleListAdapter(arrayListOf()) }

    override fun createObserver() {

        /**
         * 监听Bundle传递数据变化
         * @see mViewModel.movieStarId
         */
        monitorBundleParamMovieStarId()


        /**
         * 监听网络请求数据变化
         * @see mViewModel.movieStarInfo
         */
        monitorNetworkMovieStarInfo()

        /**
         * 监听网络请求数据变化
         * @see mViewModel.movieStarHonor
         */
        monitorNetworkMovieStarHonor()

        /**
         * 监听网络请求数据变化
         * @see mViewModel.starMovies
         */
        monitorNetworkStarMovies()

        /**
         *  监听网络加载更多数据
         *  @see mViewModel.moreStarMovies
         */
        monitorNetworkMoreStarMovies()


        /**
         * 监听网络数据
         * @see mViewModel.starRelatedPeople
         */
        monitorNetworkStarRelatedPeople()
    }

    private fun monitorNetworkStarRelatedPeople() {

        mViewModel.starRelatedPeople.observe(this) {
            parseState(it, {

                starRelatedPeopleListAdapter.setList(it.data.relations)

            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun monitorNetworkMoreStarMovies() {

        mViewModel.moreStarMovies.observe(this) {

            parseState(it, {

                starMovieListAdapter.addData(it.data.movies)
                loadMoreModule.loadMoreComplete()

                /**
                 *  判断是否还有数据
                 */
                if (it.data.paging.hasMore == false) {

                    loadMoreModule.loadMoreEnd()

                }


            }, {
                showErrorMessage(it)
            })
        }


    }

    private fun monitorNetworkStarMovies() {
        mViewModel.starMovies.observe(this) {


            parseState(it, {

                /**
                 *  设置xml数据
                 */


                mDatabind.layoutStarMovie.data = it.data.paging
                starMovieListAdapter.setList(it.data.movies)

            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun monitorNetworkMovieStarHonor() {
        mViewModel.movieStarHonor.observe(this) {

            parseState(it, {

                setMovieStarHonorBean(it.data)

            }, {

                showErrorMessage(it)
            })

        }


    }

    private fun setMovieStarHonorBean(movieStarHonorBean: MovieStarHonor.Data) {

        mViewModel.awardUrl = movieStarHonorBean.awardUrl
        /**
         * 设置xml设局
         */
        mDatabind.layoutMovieStarIntroduce.movieStarHonor = movieStarHonorBean

        // 判断影人是否获奖
        if (movieStarHonorBean.awardTotal == 0) {

            mDatabind.layoutMovieStarIntroduce.rlMovieStarHonor.visibility = View.GONE
            return

        }


    }

    private fun monitorNetworkMovieStarInfo() {


        mViewModel.movieStarInfo.observe(this) {

            parseState(it, {


                /**
                 *  重新赋值
                 *  @see movieStarAlbumBigListAdapter
                 */

                resetMovieStarAlbumBigListAdapter(it.data.photos)

                /**
                 *  设置演员基本信息
                 *
                 */
                setMovieStarInfo(it.data)

            }, {

                showErrorMessage(it)
            })

        }


    }

    private fun setMovieStarInfo(movieStarInfo: MovieStarInfo.Data) {


        /**
         *   设置xml数据
         */

        mViewModel.tvHeaderTitle = movieStarInfo.cnm
        mViewModel.tvHeaderSubTitle = movieStarInfo.enm

        mDatabind.apply {
            layoutMovieInfoHeader.layoutMoviePhotoBig.data = movieStarInfo
            layoutMovieInfoHeader.layoutMoviePhotoSmall.data = movieStarInfo
            layoutStarMovieAlbum.data = movieStarInfo
            layoutMovieStarIntroduce.data = movieStarInfo

        }


    }

    private fun resetMovieStarAlbumBigListAdapter(photos: List<String>) {

        val mutableListOfAlbumBig = mutableListOf<Album>()
        val mutableListOfAlbumSmall = mutableListOf<String>()


        for (index in 0 until photos.size) {
            val albumBean = Album()

            if (index % 3 == 0) {

                albumBean.bigPhoto = photos[index]
                mutableListOfAlbumBig.add(albumBean)

            } else {
                mutableListOfAlbumSmall.add(photos[index])


            }


        }

        val albumBean = Album()
        albumBean.smallPhoto = mutableListOfAlbumSmall

        //                mutableListOfAlbumBig.add(0,albumBean)

        movieStarAlbumBigListAdapter.setList(mutableListOfAlbumBig)


    }

    private fun monitorBundleParamMovieStarId() {


        mViewModel.movieStarId.observe(this) {


            if (it == 0) {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieStarId")


            } else {

                /**
                 *   电影人基本信息
                 */

                getMovieStarInfo(it)


                /**
                 *   电影人获奖的详情
                 */
                getMovieStarHonors(it)


                /**
                 *  电影人代表作品
                 */

                getStarMovie(it)

                /**
                 *  相关影人
                 */
                getRelatedPeople(it)


            }


        }


    }

    private fun getRelatedPeople(movieStarId: Int) {

        /**
         * 加载适配器
         * @see recyclerView_related_people
         * @see starRelatedPeopleListAdapter,
         */

        mDatabind.layoutStarRelatedPeople.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            starRelatedPeopleListAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )


        /**
         *  请求网络数据
         */

        mViewModel.getStarRelatedPeopleBean(movieStarId)


        /**
         *   item 点击事件
         */
        starRelatedPeopleListAdapter.setOnItemClickListener { adapter, view, position ->

            val mutableList = adapter.data as MutableList<StarRelatedPeople.Relation>
            val item = mutableList[position]


            /**
             *  跳转界面
             * @see MovieStarInfoBeanFragment
             */
            toMovieStarInfoBeanFragment(item.id)


        }


    }

    private fun getStarMovie(movieStarId: Int) {


        /**
         *  加载适配器
         * @see recyclerView
         * @see starMovieListAdapter
         */

        mDatabind.layoutStarMovie.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            starMovieListAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )



        mViewModel.getStarMovieBean(
            movieStarId,
            mViewModel.limit,
            mViewModel.offset
        )

        /**
         *  item 点击事件
         */
        starMovieListAdapter.setOnItemClickListener { adapter, view, position ->

            val mutableList = adapter.data as MutableList<StarMovies.Movy>
            val item = mutableList[position]


            /**
             * 跳转界面
             * @see MovieDetailFragment
             */
            nav().navigateAction(
                R.id.action_movieStarInfoBeanFragment_to_movieDetailFragment,
                MovieDetailFragmentArgs(item.id).toBundle()
            )
        }


        /**
         *   加载更多数据
         */
        loadMoreModule.setOnLoadMoreListener {

            mViewModel.offset += 10

            mViewModel.getLoadMoreStarMovieBean(movieStarId, mViewModel.limit, mViewModel.offset)

        }


    }

    private fun getMovieStarHonors(movieStarId: Int) {

        mViewModel.getMovieStarHonors(movieStarId)
    }

    private fun getMovieStarInfo(movieStarId: Int) {


        /**
         * 加载适配器
         * @see recyclerView
         * @see movieStarAlbumListAdapter
         */

        mDatabind.layoutStarMovieAlbum.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieStarAlbumBigListAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )


        /**
         * 请求网络数据
         */
        mViewModel.getMovieStarInfoBean(movieStarId)


    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_star_info_bean
    }

    override fun initView(savedInstanceState: Bundle?) {

        mDatabind.vm = mViewModel
        mDatabind.present = this

        /**
         *   初始化title
         */
        initTitle()

        /**
         *  设置全屏
         */
        setFullWindowScreen(mActivity)

        /**
         * 接收Bundle参数
         */
        receiveBundleParam()


    }

    private fun initTitle() {


        /**
         *  动态设置statusBarView的高度
         */
        initStatusBar()


        /**
         *  监听NestedScrollView的滚动动态改变title的颜色
         */
        initListener()


        /**
         *  点击事件,返回上一页
         */
        mDatabind.layoutMovieStarTitle.toolbarBack.setOnClickListener {
            nav().navigateUp()
        }


    }

    private fun initListener() {


        val changedHeight = JZUtils.dip2px(mActivity, 214F)


        mDatabind.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            val scale = v!!.scrollY.toFloat() / changedHeight
            //变化范围0-255 表示从透明到纯色背景
            val alpha: Float = if (scale * 255 >= 255) 255F else scale * 255

            val llTitle = mDatabind.layoutMovieStarTitle.llTitle
            val tvHeaderTitle = mDatabind.layoutMovieStarTitle.tvHeaderTitle
            val tvHeaderSubTitle = mDatabind.layoutMovieStarTitle.tvHeaderSubTitle

            llTitle.setBackgroundColor(Color.argb(alpha.toInt(), 212, 62, 55))

            tvHeaderTitle.setTextColor(
                Color.argb(
                    alpha.toInt(),
                    255,
                    255,
                    255
                )
            )
            tvHeaderSubTitle.setTextColor(
                Color.argb(
                    alpha.toInt(),
                    255,
                    255,
                    255
                )
            )

            /**
             *   header
             */


            mDatabind.layoutMovieStarTitle.tvHeaderTitle.text = mViewModel.tvHeaderTitle
            mDatabind.layoutMovieStarTitle.tvHeaderSubTitle.text = mViewModel.tvHeaderSubTitle


        }


    }

    private fun initStatusBar() {


        /**
         *  @see status_bar_bg
         */


        //获得view对象
        val statusBarBg = mDatabind.layoutMovieStarTitle.statusBarBg

        val status_bar_params = statusBarBg.layoutParams as LinearLayout.LayoutParams


        //获取状态栏的高度
        val height = resources.getDimensionPixelSize(
            resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
        )
        status_bar_params.height = height
        statusBarBg.layoutParams = status_bar_params


    }

    private fun receiveBundleParam() {

        arguments?.let {

            mViewModel.movieStarId.value = MovieStarInfoBeanFragmentArgs.fromBundle(it).movieStarId

        }

    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.rl_movieStar_Honor -> toMovieStarHonorDetailWebViewFragment()


        }
    }

    private fun toMovieStarHonorDetailWebViewFragment() {
        /**
         *
         *  跳转界面
         *  @see MovieStarHonorDetailWebViewFragment
         */

        nav().navigateAction(
            R.id.action_movieStarInfoBeanFragment_to_movieStarHonorDetailWebViewFragment,
            mViewModel.awardUrl?.let { MovieStarHonorDetailWebViewFragmentArgs(it).toBundle() }
        )


    }

    private fun toMovieStarInfoBeanFragment(movieStarId: Int) {

        /**
         * 跳转界面
         * @see MovieStarDetailFragment
         */
        nav().navigateAction(
            R.id.action_movieStarInfoBeanFragment_self,
            MovieStarInfoBeanFragmentArgs(movieStarId).toBundle()
        )

    }

}

