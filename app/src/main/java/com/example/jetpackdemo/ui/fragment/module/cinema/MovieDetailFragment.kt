package com.example.jetpackdemo.ui.fragment.module.cinema

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JZUtils
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.*
import com.example.jetpackdemo.databinding.FragmentMovieDetailBinding
import com.example.jetpackdemo.helper.PresenterProxyClick
import com.example.jetpackdemo.ui.activity.*
import com.example.jetpackdemo.ui.adapter.*
import com.example.jetpackdemo.util.*
import com.example.jetpackdemo.viewmodel.fragment.MovieDetailViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(),
    PresenterProxyClick {

    private val arrayList = ArrayList<Fragment>()
    private val title = arrayOf("视频", "剧照")

    private val movieStarListAdapter by lazy { MovieStarListAdapter(arrayListOf()) }
    private val movieResourceAdapter by lazy { MovieResourceAdapter(arrayListOf()) }
    private val movieRelatedInfoAdapter by lazy { MovieRelatedInfoAdapter(arrayListOf()) }
    private val relatedMoviesAdapter by lazy { RelatedMoviesAdapter(arrayListOf()) }
    private val movieCommentTagAdapter by lazy { MovieCommentTagAdapter(arrayListOf()) }
    private val movieLongCommentAdapter by lazy { MovieLongCommentAdapter(arrayListOf()) }


    override fun createObserver() {

        /**
         * 监听Bundle传递数据变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         *   监测网络请求数据变化
         *  @see  mViewModel.movieBasicData
         */
        monitorNetworkMovieBasicData()


        /**
         *   监测网络请求数据变化
         *  @see  mViewModel.movieStar
         */

        monitorNetworkMovieStar()


        /**
         *   监测网络请求数据变化
         *  @see  mViewModel.movieResource
         */
        monitorNetworkMovieResource()

        /**
         *     监测网络请求数据变化
         *  @see  mViewModel.boxOffice
         */
        monitorNetworkBoxOffice()

        /**
         *  监测网络请求数据变化
         *  @see  mViewModel.movieRelatedNews
         */
        monitorNetworkMovieRelatedNews()

        /**
         *   监测网络请求数据变化
         *  @see  mViewModel.relatedRelatedMovies
         */

        monitorNetworkRelatedRelatedMovies()

        /**
         *  监测网络请求数据变化
         *  @see  mViewModel.movieCommentTag
         */

        monitorNetworkMovieCommentTag()


        /**
         * 监测网络请求数据变化
         * @see mViewModel.movieLongComment
         */
        monitorNetworkMovieLongComment()


    }

    private fun monitorNetworkMovieLongComment() {


        mViewModel.movieLongComment.observe(this) {

            parseState(it, {

                /**
                 * 设置xml数据
                 */

                mDatabind.layoutMovieLoneCommentAll.data = it

                movieLongCommentAdapter.setList(it.data.filmReviews)

            }, {
                showErrorMessage(it)
            })
        }

    }

    private fun monitorNetworkMovieCommentTag() {

        mViewModel.movieCommentTag.observe(this) {
            parseState(it,
                {

                    movieCommentTagAdapter.setList(it.data)
                }, {
                    showErrorMessage(it)
                })
        }

    }

    private fun monitorNetworkRelatedRelatedMovies() {

        mViewModel.relatedRelatedMovies.observe(this) {
            parseState(it, {

                relatedMoviesAdapter.setList(it.data[0].items)
            }, {
                showErrorMessage(it)
            })
        }

    }

    private fun monitorNetworkMovieRelatedNews() {

        mViewModel.movieRelatedNews.observe(this) {

            parseState(it,
                {

                    movieRelatedInfoAdapter.setList(it.data.newsList)
                }, {
                    showErrorMessage(it)
                })

        }


    }

    private fun monitorNetworkBoxOffice() {

        mViewModel.boxOffice.observe(this) {

            parseState(it,
                {

                    setBoxOffice(it)

                }, {
                    showErrorMessage(it)
                })
        }

    }

    private fun setBoxOffice(boxOffice: BoxOffice) {

        mViewModel.boxOfficeUrl = boxOffice.url
        /**
         *  设置xml数据
         */
        mDatabind.layoutBoxoffice.data = boxOffice.mbox

    }

    private fun monitorNetworkMovieResource() {
        mViewModel.movieResource.observe(this) {

            parseState(it, {
                movieResourceAdapter.setList(it.data)

            }, {
                showErrorMessage(it)
            })
        }

    }

    private fun monitorNetworkMovieStar() {

        mViewModel.movieStar.observe(this) { resultState ->

            parseState(resultState, {

                //赋值xml
                mDatabind.layoutMovieStar.data = it

                movieStarListAdapter.setList(parseData(it))

            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun parseData(it: MovieStar): Collection<MovieStar.Data>? {


        val mutableListOf = ArrayList<MovieStar.Data>()

        /**
         *   解析数据
         */
        if (it != null) {

            val data = it.data

            for (i in data) {


                for (j in i) {

                    mutableListOf.add(j)

                }
            }


        }



        return mutableListOf


    }

    private fun monitorNetworkMovieBasicData() {


        mViewModel.movieBasicData.observe(this) { resultState ->

            parseState(resultState, {

                /**
                 *  电影基本信息
                 */
                setMovieBasicData(it)

                /**
                 *  3  电影人员 剧照&视频
                 */

                initFragment(mViewModel.movieId.value)

                /**
                 *  初始化title
                 */
                initTitle()

            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun initTitle() {

        /**
         * 头部titlebar
         */
        mDatabind.noBackTitle.toolbar.setNoBackTitle(
            "电影",
            defaultBackgroundColor = resources.getColor(R.color.transparent),

            )

        /**
         *  动态设置statusBarView的高度
         */
        initStatusBar()

        /**
         *  监听NestedScrollView的滚动动态改变title的颜色
         */
        initListener()


    }

    private fun initListener() {


        val changedHeight = JZUtils.dip2px(mActivity, 214F)

        mDatabind.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            val scale = v!!.scrollY.toFloat() / changedHeight
            //变化范围0-255 表示从透明到纯色背景
            val alpha: Float = if (scale * 255 >= 255) 255F else scale * 255

//            判断字段状态
            val color = if (mViewModel.movieBean != null) {
                Color.parseColor(mViewModel.movieBean.backgroundColor)

            } else {

                resources.getColor(R.color.colorPrimary)
            }


            //   将 color Int 转换成RGB
            val red = color and 0xff0000 shr 16
            val green = color and 0x00ff00 shr 8
            val blue = color and 0x0000ff

            val llTitle = mDatabind.layoutMovieDetailTitle.llTitle
            val lvTitleWish = mDatabind.layoutMovieDetailTitle.lvTitleWish
            val lvTitleWatched = mDatabind.layoutMovieDetailTitle.lvTitleWatched
            val tvTitleWish = mDatabind.layoutMovieDetailTitle.tvTitleWish
            val tvTitleWatched = mDatabind.layoutMovieDetailTitle.tvTitleWatched
            val tvMovieName = mDatabind.layoutMovieDetailTitle.tvMovieName



            llTitle.setBackgroundColor(Color.argb(alpha.toInt(), red, green, blue))
            lvTitleWish.setBackgroundColor(Color.argb(alpha.toInt(), red, green, blue))
            lvTitleWatched.setBackgroundColor(Color.argb(alpha.toInt(), red, green, blue))

            //  想看
            tvTitleWish.setTextColor(Color.argb(alpha.toInt(), 255, 255, 255))
            //  看过
            tvTitleWatched.setTextColor(Color.argb(alpha.toInt(), 255, 255, 255))
            //  电影名字
            tvMovieName.setTextColor(Color.argb(alpha.toInt(), 255, 255, 255))

            tvTitleWish.text = "想看"
            tvTitleWatched.text = "看过"


            //电影名字
            tvMovieName.text = mViewModel.movieBean.nm


            //判断字段状态

            if (mViewModel.movieBean.sc != 0.0) {

                val tvMovieSc = mDatabind.layoutMovieDetailTitle.tvMovieSc
                tvMovieSc.visibility = View.VISIBLE
                //  电影评分
                tvMovieSc.setTextColor(Color.argb(alpha.toInt(), 254, 159, 14))
                tvMovieSc.text = mViewModel.movieBean.sc.toString()

            } else {

                val lvShowWish = mDatabind.layoutMovieDetailTitle.lvShowWish
                lvShowWish.visibility = View.VISIBLE
                val tvMovieWishHeader = mDatabind.layoutMovieDetailTitle.tvMovieWishHeader

                tvMovieWishHeader.text = countWishPeople(mViewModel.movieBean.wish)
                tvMovieWishHeader.setTextColor(Color.argb(alpha.toInt(), 254, 159, 14))

                tvMovieWishHeader.setTextColor(Color.argb(alpha.toInt(), 255, 255, 255))

            }

        }
    }

    private fun initStatusBar() {


        /**
         *  @see status_bar_bg
         *
         */
        val statusBarBg = mDatabind.layoutMovieDetailTitle.statusBarBg
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

    private fun setMovieBasicData(movieBasicDataBean: MovieBasicData) {

        //保存电影名字
        mViewModel.movieName = movieBasicDataBean.data.movie.nm


        /**
         *   电影基本信息
         */
        setMovieBasicInfo(movieBasicDataBean.data.movie)


        /**
         *   电影评分
         */

        setMovieScore(mViewModel.movieBean)


    }

    private fun setMovieScore(movieBean: MovieBasicData.Movie) {

        /**
         *
         *     判断当前电影是否有电影评分
         */
        if (movieBean.sc != 0.0) {

            mDatabind.layoutMovieScore.data = movieBean

            mDatabind.layoutMovieScore.lvMovieScore.visibility = View.VISIBLE
            mDatabind.layoutMovieWish.lvMovieWish.visibility = View.GONE


            setCatEyeScore(movieBean)

        } else {

            mDatabind.layoutMovieScore.lvMovieScore.visibility = View.GONE
            mDatabind.layoutMovieWish.lvMovieWish.visibility = View.VISIBLE


        }


    }

    private fun setCatEyeScore(movie: MovieBasicData.Movie) {

        /**
         *   电影打分
         *
         */

        setMovieScoreProgressBar(movie.distributions)


    }

    private fun setMovieScoreProgressBar(distributions: List<MovieBasicData.Distribution>) {


        val heightScore = distributions[0].proportion
        val middleScore = distributions[1].proportion
        val lowScore = distributions[2].proportion

        val heightScoreEndIndex = heightScore.indexOf(".")
        val middleScoreIndex = middleScore.indexOf(".")
        val lowScoreIndex = lowScore.indexOf(".")


        val toIntHeightScore = heightScore.run {
            substring(0, heightScoreEndIndex)

        }.toInt()

        val toIntMiddleScore = middleScore.run {
            substring(0, middleScoreIndex)

        }.toInt()

        val toIntLowScore = lowScore.run {
            substring(0, lowScoreIndex)
            substring(0, lowScoreIndex)

        }.toInt()

        mDatabind.layoutMovieScore.movieScoreLevelHeight.progress = toIntHeightScore
        mDatabind.layoutMovieScore.movieScoreLevelMiddle.progress = toIntMiddleScore
        mDatabind.layoutMovieScore.movieScoreLevelLow.progress = toIntLowScore

    }

    private fun setMovieBasicInfo(movieBean: MovieBasicData.Movie) {


        /**
         *  赋值xml数据
         */
        mViewModel.movieBean = movieBean
        mDatabind.layoutMovieBasicInfo.data = movieBean
        mDatabind.layoutMovieWish.data = movieBean
        mDatabind.layoutIntroduce.data = movieBean



        mDatabind.rlBg.setBackgroundColor(Color.parseColor(movieBean.backgroundColor))



        mDatabind.layoutIntroduce.visibilityControl.setOnClickListener {

            if (mViewModel.flag) {

                mDatabind.layoutIntroduce.showOpenTv.visibility = View.GONE
                mDatabind.layoutIntroduce.closeShrinkTv.visibility = View.VISIBLE

                mViewModel.flag = false

            } else {

                mDatabind.layoutIntroduce.showOpenTv.visibility = View.VISIBLE
                mDatabind.layoutIntroduce.closeShrinkTv.visibility = View.GONE


                mViewModel.flag = true

            }


        }


    }

    private fun monitorBundleParamMovieId() {


        mViewModel.movieId.observe(this) {

            if (it != 0) {

                /**
                 *  发送网络请求数据
                 */
                setMovieInfoByRequest(it)


            } else {

                it.toString()
                    .loge(javaClass.simpleName + ".bundle.params.movieId")


            }

        }

    }

    private fun setMovieInfoByRequest(movieId: Int) {

        /**
         * 开启加载框
         */
        showLoading()

        /**
         *  1 设置电影基本信息
         */
        setMovieInfo(movieId)


        /**
         *
         *  2 获取电影人员详情
         */

        setMovieStarList(movieId)


        /**
         *
         *  4   影片资料
         */
        setMovieDetailsInfo(movieId)

        /**
         *
         *     5  票房
         */
        getBoxOffice(movieId)

        /**
         *
         *    6  电影相关资讯
         */
        setMovieRelatedNews(movieId)


        /**
         *
         *  7 相关电影
         */
        setRelatedMovies(movieId)

        /**
         *  8  观众热评
         *
         */
        setMoviesComment(movieId)

        /**
         *
         *    9 热门长评
         */
        setMovieLongComment(movieId)


        /**
         *    10 全部热门长评
         *
         */
        setMovieLongCommentList(movieId)


        /**
         *  关闭加载框
         */
        hideLoading()

    }

    private fun setMovieLongCommentList(movieId: Int) {


        /**
         *   跳转
         *  @see MovieLongCommentListActivity
         */
        //            MovieLongCommentListActivity.startAction(this, movieId)
        mDatabind.layoutMovieLoneCommentAll.rlTotalLoneComment.setOnClickListener {

            nav().navigateAction(
                R.id.action_movieDetailFragment_to_movieLongCommentListFragment,
                MovieLongCommentListFragmentArgs(movieId).toBundle()
            )


        }

    }

    private fun setMovieLongComment(movieId: Int) {


        /**
         *  跳转界面
         * @see WriteLongCommentFragment
         */

        mDatabind.layoutMovieLoneComment.tvWriteComment.setOnClickListener {

            nav().navigateAction(R.id.action_movieDetailFragment_to_writeLongCommentFragment)

        }


        /**
         *  加载适配器 adapter
         * @see recyclerView
         * @see movieLongCommentAdapter
         */

        mDatabind.layoutMovieLoneComment.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieLongCommentAdapter,
            mActivity
        )


        /**
         *    请求网络数据
         */
        mViewModel.getMovieLongComment(movieId)


    }

    private fun setMoviesComment(movieId: Int) {

        /**
         * 加载适配器 adapter
         * @see recyclerView_movie_comment_tag
         * @see movieCommentTagAdapter
         */

        mDatabind.layoutViewerComment.recyclerView.loadRecyclerAdapterByGridLayoutManager(
            movieCommentTagAdapter,
            mActivity,
            3
        )


        /**
         *   请求网络数据
         */
        mViewModel.getMoviesComment(movieId)

        /**
         *  item 点击事件
         */
        movieCommentTagAdapter.setOnItemClickListener { adapter, view, position ->

            val mutableList = adapter.data as MutableList<MovieCommentTag.Data>
            val item = mutableList[position]


            if (mViewModel.prePosition == position) {

                mutableList[position].isSelected = true
            } else {

                //当前item currentPosition位置 和 上一次点击位置prePosition  不一样，
                // 则将集合mutableList所有对象 isSelected = false,且mutableList#currentPosition = true
                for (item in mutableList) {
                    item.isSelected = false
                }
                mutableList[position].isSelected = true


            }

            //记录当前的选中currentPosition
            mViewModel.prePosition = position

            /**
             * 跳转界面
             * @see MovieShortCommentFragment
             */

            nav().navigateAction(
                R.id.action_movieDetailFragment_to_movieShortCommentFragment,
                MovieShortCommentFragmentArgs(
                    mViewModel.movieName,
                    mutableList.toTypedArray(),
                    item.tag,
                    item.movieId
                ).toBundle()
            )
        }


    }

    private fun setRelatedMovies(movieId: Int) {

        /**
         * 加载适配器 adapter
         * @see recyclerView_related_movies
         * @see relatedMoviesAdapter
         */

        mDatabind.layoutMovieRelatedMovies.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            relatedMoviesAdapter,
            mActivity,
            RecyclerView.HORIZONTAL

        )

        /**
         *   请求网络数据
         */
        mViewModel.getRelatedMovies(movieId)


        /**
         *  item点击事件
         */
        relatedMoviesAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            val mutableList = adapter.data as MutableList<RelatedMovies.Item>
            val item = mutableList[currentPosition]


            /**
             *   跳转界面
             *   @see MovieDetailFragment
             */
            //            MovieDetailActivity.startAction(this, item.desc.toInt())
            nav().navigateAction(
                R.id.action_to_movieDetailFragment,
                MovieDetailFragmentArgs(item.desc.toInt()).toBundle()
            )

        }


    }

    private fun setMovieRelatedNews(movieId: Int) {
        /**
         * 加载适配器 adapter
         *  @see recyclerView_movieRelateNews
         *  @see movieRelatedInfoAdapter
         */
        mDatabind.layoutMovieRelateNews.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieRelatedInfoAdapter,
            mActivity
        )


        /**
         *  请求网络数据
         */
        mViewModel.getMovieRelatedNews(movieId)


        //            MovieRelatedNewsActivity.startAction(this, movieId, mViewModel.movieName)
        /**
         * 跳转界面
         * @see MovieRelatedNewsFragment
         */
        mDatabind.layoutMovieRelateNews.linearLayoutTotalNews.setOnClickListener {

            nav().navigateAction(
                R.id.action_movieDetailFragment_to_movieRelatedNewsFragment,
                MovieRelatedNewsFragmentArgs(movieId, mViewModel.movieName).toBundle()
            )
        }


    }

    private fun getBoxOffice(movieId: Int) {

        mViewModel.getBoxOffice(movieId)

    }

    private fun setMovieDetailsInfo(movieId: Int) {


        /**
         * 加载适配器 adapter
         * @see  recyclerView_movieResource
         * @see   movieResourceAdapter
         */

        mDatabind.layoutMovieInfo.recyclerView.loadRecyclerAdapterByGridLayoutManager(
            movieResourceAdapter,
            mActivity,
            2
        )

        /**
         *   请求网络数据
         */
        mViewModel.getMovieResource(mViewModel.movieId.value)


        /**
         *  item点击事件
         *
         */
        movieResourceAdapter.setOnItemClickListener { adapter, view, currentPosition ->

            val mutableList = adapter.data as MutableList<MovieResource.Data>
            val item = mutableList[currentPosition]

            val movieId = mViewModel.movieId.value




            when (item.title) {
                "出品发行" -> nav().navigateAction(
                    R.id.action_movieDetailFragment_to_relatedCompanyFragment,
                    RelatedCompanyFragmentArgs(movieId).toBundle()

                )

                "技术参数" -> nav().navigateAction(
                    R.id.action_movieDetailFragment_to_movieTechnicalsFragment,
                    MovieTechnicalsFragmentArgs(movieId).toBundle()
                )
                "电影原声" -> nav().navigateAction(
                    R.id.action_movieDetailFragment_to_movieResourceMusicFragment,
                    MovieResourceMusicFragmentArgs(movieId).toBundle()
                )
            }

        }


    }

    private fun initFragment(movieId: Int) {

        arrayList.clear()

        val movieStarVideoFragment = MovieStarVideoFragment.newInstance(movieId)
        val movieStarPhotoFragment =
            MovieStarPhotoFragment.newInstance(mViewModel.movieName, mViewModel.movieBean.photos)

        arrayList.add(movieStarVideoFragment)
        arrayList.add(movieStarPhotoFragment)

        val noScrollViewPager = mDatabind.layoutMoviePhoto.noScrollViewPager

        val adapter = MyViewPager(arrayList, childFragmentManager) //创建适配器
        noScrollViewPager.adapter = adapter      //将适配器添加到NoScrollViewPager

        mDatabind.layoutMoviePhoto.tabLayout.setViewPager(noScrollViewPager, title)


    }

    private fun setMovieStarList(movieId: Int) {


        /**
         *  加载适配器 adapter
         *   @see mDatabind.layoutMovieStar.recyclerView
         *   @see  movieStarListAdapter
         */

        mDatabind.layoutMovieStar.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieStarListAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )


        /**
         *    请求网络数据
         */

        mViewModel.getMovieStarList(movieId)

        /**
         *   item点击事件
         */
        movieStarListAdapter.setOnItemClickListener { adapter, view, currentPosition ->

            val mutableList = adapter.data as MutableList<MovieStar.Data>

            // 跳转 MovieStarInfoBeanActivity
            val item = mutableList[currentPosition]


            //            MovieStarInfoBeanActivity.startAction(this, item.id)

            nav().navigateAction(
                R.id.action_movieDetailFragment_to_movieStarInfoBeanFragment,
                MovieStarInfoBeanFragmentArgs(item.id).toBundle()
            )
        }


    }

    private fun setMovieInfo(movieId: Int) {

        mViewModel.getMovieBasicData(movieId)

    }

    override fun layoutId(): Int {

        return R.layout.fragment_movie_detail
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         * 绑定数据
         */
        mDatabind.presenter = this
        mDatabind.vm = mViewModel


        /**
         *  设置全屏
         */
        setFullWindowScreen(mActivity)


        /**
         * 接收Bundle参数
         */
        receiveBundleParam()

        /**
         * 下拉刷新框
         */
        iniSwipeRefreshLayout()


    }

    private fun iniSwipeRefreshLayout() {


    }

    private fun receiveBundleParam() {


        arguments?.let {

            mViewModel.movieId.value = MovieDetailFragmentArgs.fromBundle(it).movieId

        }


    }

    fun showLoading() {

        if (!mDatabind.progressLayout.isContent) {

            mDatabind.progressLayout.showLoading()
        }

    }

    fun hideLoading() {

        mDatabind.progressLayout.showContent()
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.iv_img_pic -> toMovieVideoFragment()//跳转界面
            R.id.toolbar_back -> backToLastFragment() //返回上一页
            R.id.lv_box_office -> toWebViewFragment()

        }
    }

    private fun toWebViewFragment() {


        mViewModel.boxOfficeUrl.let {

            /**
             *  点击跳转界面
             *  @see WebViewFragment
             */

            nav().navigateAction(
                R.id.action_movieDetailFragment_to_webViewFragment,
                WebViewFragmentArgs(it, mViewModel.movieName).toBundle()
            )

        }
    }

    private fun backToLastFragment() {


        nav().navigateUp()
    }

    private fun toMovieVideoFragment() {

        nav().navigateAction(
            R.id.action_movieDetailFragment_to_movieVideoFragment,
            MovieVideoFragmentArgs(mViewModel.movieBean.id, null).toBundle()
        )
    }


}