package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.bean.HotMovieList
import com.example.jetpackdemo.databinding.FragmentHotShowBinding
import com.example.jetpackdemo.ui.adapter.HotShowAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.makeToast
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.HotShowViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class HotShowFragment : BaseFragment<HotShowViewModel, FragmentHotShowBinding>(),
    BGARefreshLayout.BGARefreshLayoutDelegate {
    private val handler = object : Handler() {}
    private lateinit var mBGARefreshLayout: BGARefreshLayout
    private val hotShowAdapter by lazy { HotShowAdapter(arrayListOf()) }


    override fun createObserver() {

        /**
         *  加载数据
         */
        mViewModel.hotMovieListBean.observe(this) { resultState ->

            parseState(resultState, {


                hotShowAdapter.setList(it.data.hot)

                //关闭刷新
                hideLoading()
                //  获取电影id
                getHotMovieIds(it.data.movieIds)

            }, {

                showErrorMessage(it)
            })

        }


        /**
         *   加载更多数据
         */
        mViewModel.moreMovieListBean.observe(this) { resultState ->

            parseState(resultState, {


                hotShowAdapter.addData(it.data.hot)
                //加载完毕
                loadMoreCompleted()


            }, {

                showErrorMessage(it)
            })

        }

    }

    override fun layoutId(): Int {

        return R.layout.fragment_hot_show
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *
         *    初始化BGARefreshLayout
         */
        initFreshLayout()

        initHotMovieList()

    }

    private fun initHotMovieList() {

        /**
         *    加载适配器
         *  @see adapterProvider
         *  @see recyclerView
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(hotShowAdapter, mActivity)


        showLoading()

        /**
         * 请求网络数据
         */
        mViewModel.getHostList()

        /**
         *      ItemChild点击事件
         */
        hotShowAdapter.setOnItemChildClickListener { adapter, view, position ->
            val data = adapter.data as MutableList<HotMovieList.Hot>
            val hotBean = data.get(position)
            val movieId = hotBean.id
            when (view.id) {


                R.id.fl_hot_movie -> nav().navigateAction(
                    R.id.action_to_movieVideoFragment,
                    MovieVideoFragmentArgs(movieId, null).toBundle()
                )

                R.id.ll_movie_detail -> {

                    nav().navigateAction(
                        R.id.action_to_movieDetailFragment,
                        MovieDetailFragmentArgs(movieId).toBundle()
                    )
                }
                R.id.btn_buy -> makeToast(context, "业务未处理....")
                R.id.fl_hot_movie_preview -> nav().navigateAction(
                    R.id.action_to_movieVideoFragment,
                    MovieVideoFragmentArgs(movieId, null).toBundle()
                )

                R.id.ll_movie_detail_preview -> nav().navigateAction(
                    R.id.action_to_movieDetailFragment,
                    MovieDetailFragmentArgs(movieId).toBundle()
                )
                else -> makeToast(context, "业务未处理....")

            }


        }


    }

    private fun initFreshLayout() {

        mBGARefreshLayout = mDatabind.bGARefreshLayout

        val headerView =
            LayoutInflater.from(context).inflate(R.layout.layout_header_add, null, false)
        mBGARefreshLayout.setCustomHeaderView(headerView, false)//添加头布局


        mBGARefreshLayout.setRefreshViewHolder(BGANormalRefreshViewHolder(context, true))  //加载适配器
        mBGARefreshLayout.setDelegate(this)

    }


    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {

        mViewModel.currentPager = 0

        mViewModel.getHostList()


    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {


        /**
         *
         *      判断是否还有数据
         */

        if (mViewModel.currentPager > mViewModel.totalPager) {

            makeToast(mActivity, "没有数据了")



            return false
        }


        val stringBuilder = StringBuilder()
        val movieIdList = mViewModel.mMovieIds[mViewModel.currentPager] //当前current的下mMovieIds

        for (i in 0 until movieIdList.size) {

            if (i < movieIdList.size - 1) {

                stringBuilder.append(movieIdList[i])
                stringBuilder.append(",")

            } else {
                stringBuilder.append(movieIdList[i])


            }


        }


        /**
         *   发送请求
         */


        "currentPager:\t ${mViewModel.currentPager} \t totalPager:\t${mViewModel.totalPager}".loge()
        "stringBuilder:\t $stringBuilder".loge()



        mViewModel.getLoadMoreHotMovieList(0, stringBuilder.toString())


        return true

    }


    fun showLoading() {

        mDatabind.progressLayout.showLoading()

    }

    fun hideLoading() {


        handler.postDelayed(Runnable {
            mBGARefreshLayout.endRefreshing()  //停止刷新

            mDatabind.progressLayout.showContent()
        }, 200)


    }


    fun getHotMovieIds(movieIds: List<Int>) {

        mViewModel.totalPager = movieIds.size / 12    //单位组
        val leftCount = movieIds.size % 12      //剩余


        //n组
        for (i in 0 until mViewModel.totalPager) {
            val integers = mutableListOf<Int>()
            for (j in i * 12 until movieIds.size) {
                integers.add(movieIds[j])
                mViewModel.mMovieIds.add(i, integers)
                if (integers.size == 12) break
            }
        }

        //n +1组
        val integers = mutableListOf<Int>()

        for (i in 0 until leftCount) {

            val element = movieIds.get(mViewModel.totalPager * 12 + i)
            integers.add(element)

        }
        mViewModel.mMovieIds.add(mViewModel.totalPager + 1, integers)

        mViewModel.totalPager++

    }

    fun loadMoreCompleted() {

        mViewModel.currentPager++

        /**
         *  关闭上拉刷新
         */

        handler.postDelayed(Runnable { mBGARefreshLayout.endLoadingMore() }, 500)


    }


}