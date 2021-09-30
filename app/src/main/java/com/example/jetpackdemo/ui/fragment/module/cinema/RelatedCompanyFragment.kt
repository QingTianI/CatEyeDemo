package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentRelatedCompanyBinding
import com.example.jetpackdemo.ui.adapter.MoviePublicCompanyAdapter
import com.example.jetpackdemo.ui.adapter.OtherCompanyAdapter
import com.example.jetpackdemo.ui.adapter.RelatedCompanyAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.RelatedCompanyViewModel
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge

class RelatedCompanyFragment :
    BaseFragment<RelatedCompanyViewModel, FragmentRelatedCompanyBinding>() {

    private val relatedCompanyAdapter by lazy { RelatedCompanyAdapter(arrayListOf()) }
    private val moviePublicCompanyAdapter by lazy { MoviePublicCompanyAdapter(arrayListOf()) }
    private val otherCompanyAdapter by lazy { OtherCompanyAdapter(arrayListOf()) }


    override fun createObserver() {

        /**
         * 监测Bundle传输数据变化
         * @see mViewModel.movieId
         */
        monitorBundleParamMovieId()


        /**
         * 监测网络请求数据变化
         * @see mViewModel.movieRelatedCompanies
         */
        monitorNetworkMovieRelatedCompanies()
    }

    private fun monitorNetworkMovieRelatedCompanies() {

        mViewModel.movieRelatedCompanies.observe(this) {

            parseState(it,
                {

                    relatedCompanyAdapter.setList(it.data[0].items)
                    moviePublicCompanyAdapter.setList(it.data[1].items)
                    otherCompanyAdapter.setList(it.data[2].items)

                }, {
                    showErrorMessage(it)
                })
        }

    }

    private fun monitorBundleParamMovieId() {

        mViewModel.movieId.observe(this) {

            if (it != 0) {

                setMovieRelatedCompanies(mViewModel.movieId.value)
            } else {

               it.toString().loge(javaClass.simpleName + ".bundle.params.movieId")


            }

        }


    }

    private fun setMovieRelatedCompanies(movieId: Int) {

        /** 加载适配器 adapter
         * @see recyclerView_product
         * @see relatedCompanyAdapter
         */

        mDatabind.recyclerViewProduct.loadRecyclerAdapterByLinearLayoutManager(
            relatedCompanyAdapter,
            mActivity
        )


        /** 加载适配器 adapter
         * @see recyclerView_publish
         * @see moviePublicCompanyAdapter
         */

        mDatabind.recyclerViewPublish.loadRecyclerAdapterByLinearLayoutManager(
            moviePublicCompanyAdapter,
            mActivity
        )


        /** 加载适配器 adapter
         * @see recyclerView_other
         * @see otherCompanyAdapter
         */

        mDatabind.recyclerViewOther.loadRecyclerAdapterByLinearLayoutManager(
            otherCompanyAdapter,
            mActivity
        )

        /**
         *   请求网路数据
         */
        mViewModel.getMovieRelatedCompanies(movieId)


    }

    override fun layoutId(): Int {
        return R.layout.fragment_related_company
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

//
//        mDatabind.normaTitle.toolbar.setNormalTitle(
//            this,
//            "出品发行",
//        )


    }

    private fun receiverBundleParam() {

        arguments?.let {

            mViewModel.movieId.value = RelatedCompanyFragmentArgs.fromBundle(it).movieId
        }


    }

}