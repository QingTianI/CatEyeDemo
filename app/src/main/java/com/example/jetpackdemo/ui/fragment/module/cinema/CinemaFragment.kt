package com.example.jetpackdemo.ui.fragment.module.cinema

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.data.model.Constant
import com.example.jetpackdemo.data.model.bean.PickCity
import com.example.jetpackdemo.databinding.FragmentCinemaBinding
import com.example.jetpackdemo.ui.activity.CityPickerActivity
import com.example.jetpackdemo.ui.adapter.MyViewPager
import com.example.jetpackdemo.viewmodel.fragment.CinemaViewModel

class CinemaFragment : BaseFragment<CinemaViewModel, FragmentCinemaBinding>() {

    private var arrayList = ArrayList<Fragment>()
    private val title = arrayOf("热映", "影院", "待映")


    override fun createObserver() {


    }

    override fun layoutId() = R.layout.fragment_cinema


    override fun initView(savedInstanceState: Bundle?) {

        initSharedPreferences()
        initEvent()
        initFragment()   //初始化fragment

    }

    private fun initFragment() {



        val hotShowFragment = HotShowFragment()
        val movieTheatreFragment = MovieTheatreFragment()
        val waitShowFragment = WaitShowFragment()


        arrayList.add(hotShowFragment)
        arrayList.add(movieTheatreFragment)
        arrayList.add(waitShowFragment)


        val adapter = MyViewPager(arrayList, childFragmentManager)   // 创建Adapter
        mDatabind.viewPager.adapter = adapter
        mDatabind.tabLayout.setViewPager(mDatabind.viewPager, title)
    }

    private fun initEvent() {
       mDatabind.tvAddress.setOnClickListener {

            val intent = Intent(context, CityPickerActivity::class.java)
            startActivityForResult(intent, 1)


        }


    }

    private fun initSharedPreferences() {
        val sharedPreferences = context?.getSharedPreferences("CityDataInfo", Context.MODE_PRIVATE)
        val cityName = sharedPreferences?.getString(Constant.KEY_CITYNAMEE, "")
        mDatabind.pickCityBean = cityName?.let { PickCity.Ct(0, it,"") }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            1 -> {
                if (resultCode == Activity.RESULT_OK) {

                    val cityName = data?.getStringExtra("cityName")
                  mDatabind.pickCityBean = cityName?.let { PickCity.Ct(0, it,"") }


                }

            }
        }

    }

}