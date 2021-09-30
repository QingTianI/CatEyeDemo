package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieTechnicalsBinding
import com.example.jetpackdemo.ui.adapter.MovieTechnicalsAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.setNormalTitle
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.MovieTechnicalsViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class MovieTechnicalsFragment :
    BaseFragment<MovieTechnicalsViewModel, FragmentMovieTechnicalsBinding>() {

    private val movieTechnicalsAdapter by lazy { MovieTechnicalsAdapter(arrayListOf()) }

    override fun createObserver() {

        /**
         * 监听Bundle传输数变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         *  监测网络请求数据变化
         *  @see mViewModel.movieTechnicals
         */
        monitorNetworkMovieTechnicals()

    }

    private fun monitorNetworkMovieTechnicals() {

        mViewModel.movieTechnicals.observe(this) {
            parseState(it, {

                movieTechnicalsAdapter.setList(it.data.items)
            }, {
                showErrorMessage(it)
            })

        }


    }

    private fun monitorBundleParamMovieId() {

        mViewModel.movieId.observe(this) {

            if (it != 0) {

                setMovieTechnicals(mViewModel.movieId.value)

            } else {

            it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")


            }


        }
    }

    private fun setMovieTechnicals(movieId: Int) {


        /**
         *  加载适配器 adapter
         *  @see recyclerView
         *  @see  movieTechnicalsAdapter
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieTechnicalsAdapter,
            mActivity
        )



        mViewModel.getMovieTechnicals(movieId)

    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_technicals
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

        mDatabind.normalTitle.toolbar.setNormalTitle(
            this,
            "技术参数"
        )

    }

    private fun receiverBundleParam() {

        arguments?.let {

            mViewModel.movieId.value = MovieTechnicalsFragmentArgs.fromBundle(it).movieId
        }

    }

}