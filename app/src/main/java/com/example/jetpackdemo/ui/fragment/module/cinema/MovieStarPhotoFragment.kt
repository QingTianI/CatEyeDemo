package com.example.jetpackdemo.ui.fragment.module.cinema

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.databinding.FragmentMovieStarPhotoBinding
import com.example.jetpackdemo.ui.adapter.MovieStarPhotoAdapter
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.viewmodel.fragment.MovieStarPhotoViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.util.loge
import java.util.*

class MovieStarPhotoFragment :
    BaseFragment<MovieStarPhotoViewModel, FragmentMovieStarPhotoBinding>() {
    private val movieStarPhotoAdapter by lazy { MovieStarPhotoAdapter(arrayListOf()) }

    override fun createObserver() {

        /**
         *  监听Bundle数据变化
         *  @see mViewModel.movieName
         */

        monitorBundleParamMovieName()

        /**
         *  监听Bundle数据变化
         *  @see mViewModel.photos
         */
        monitorBundleParamPhotos()

    }

    private fun monitorBundleParamPhotos() {

        mViewModel.photos.observe(this) {

            if (it != null) {


                movieStarPhotoAdapter.setList(it)

                /**
                 *  设置RecyclerView
                 */
                initRV()

            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.photos")


            }
        }

    }

    private fun monitorBundleParamMovieName() {

        mViewModel.movieName.observe(this) {

            if (!it.isEmpty()) {


            } else {

                it.toString().loge(javaClass.simpleName + ".bundle.params.movieName")


            }

        }


    }

    private fun initRV() {


        /**
         *  加载适配器 adapter
         *  @see  mDatabind.recyclerView
         *  @see movieStarPhotoAdapter
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieStarPhotoAdapter,
            mActivity,
            RecyclerView.HORIZONTAL
        )

        /**
         *    item 点击
         */
        movieStarPhotoAdapter.setOnItemClickListener { adapter, view, position ->

            nav().navigateAction(
                R.id.action_to_movieStarPhotoDetailsFragment,
                mViewModel.photos.value?.let {
                    MovieStarPhotoDetailsFragmentArgs(
                        mViewModel.movieName.value,
                        it.toTypedArray()
                    ).toBundle()
                }
            )

        }


    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_star_photo
    }

    override fun initView(savedInstanceState: Bundle?) {
        /**
         *  接收Bundle数据
         *
         */
        receiverBundleParam()

    }

    private fun receiverBundleParam() {


        arguments?.let {
            mViewModel.movieName.value = it.getString(MOVIENAME).toString()
            mViewModel.photos.value = it.getStringArrayList(PHOTOS)
        }
    }


    companion object {

        const val MOVIENAME = "movieName"
        const val PHOTOS = "photos"
        fun newInstance(movieName: String, photos: List<String>) =
            MovieStarPhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIENAME, movieName)
                    putStringArrayList(PHOTOS, photos as ArrayList<String>)
                }
            }
    }


}