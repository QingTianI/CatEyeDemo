package com.example.jetpackdemo.ui.fragment.module.cinema

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JZUtils.dip2px
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseFragment
import com.example.jetpackdemo.custom.PopupWindowManager
import com.example.jetpackdemo.data.model.Constant
import com.example.jetpackdemo.data.model.bean.Filter
import com.example.jetpackdemo.data.model.bean.TabEntity
import com.example.jetpackdemo.databinding.FragmentMovieTheatreBinding
import com.example.jetpackdemo.ui.adapter.*
import com.example.jetpackdemo.util.loadRecyclerAdapterByGridLayoutManager
import com.example.jetpackdemo.util.loadRecyclerAdapterByLinearLayoutManager
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.fragment.MovieTheatreViewModel
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.loge
import me.hgj.jetpackmvvm.ext.util.logi

class MovieTheatreFragment : BaseFragment<MovieTheatreViewModel, FragmentMovieTheatreBinding>(),
    View.OnClickListener {


    private var subItemXXList: MutableList<Filter.SubItemXX> = mutableListOf()
    lateinit var subItemXXXXXXList: MutableList<Filter.SubItemXXXXXX>
    lateinit var subItemXXXXXList: MutableList<Filter.SubItemXXXXX>


    private lateinit var cityPickerChangedReceiver: CityPickerChangedReceiver

    private lateinit var popupWindowManagerBrand: PopupWindowManager
    private lateinit var popupWindowManagerDistance: PopupWindowManager
    private lateinit var popupWindowManagerHalType: PopupWindowManager
    private lateinit var popupWindowManagerWholeCity: PopupWindowManager


    private val tv_brand by lazy { mDatabind.layoutHeader.tvBrand }
    private val tv_whole_city by lazy { mDatabind.layoutHeader.tvWholeCity }
    private val tv_short_distance by lazy { mDatabind.layoutHeader.tvShortDistance }
    private val tv_choose by lazy { mDatabind.layoutHeader.tvChoose }


    // View
    private lateinit var view_brand: View
    private lateinit var view_distance: View
    private lateinit var view_hall_type: View
    private lateinit var view_whole_city: View

    //Adapter
    private lateinit var halfTypeAdapter: HalfTypeAdapter
    private lateinit var halfTypeServiceAdapter: HalfTypeServiceAdapter
    private lateinit var cityAreaParentAdapter: CityAreaParentAdapter
    private lateinit var subwaySubAdapter: SubwaySubAdapter
    private lateinit var subwayParentAdapter: SubwayParentAdapter
    private val movieTheatreAdapter by lazy { MovieTheatreAdapter(arrayListOf()) }

    //Header
    private lateinit var view_header: View

    override fun createObserver() {

        /**
         * ??????????????????
         * @see mViewModel.cityId
         */

        monitorIntentParamCityId()

        /**
         *  ????????????????????????
         * @see mViewModel.cinemaList
         */

        monitorNetworkCinemaList()


        /**
         *  ??????????????????????????????
         *  @see mViewModel.filter
         */

        monitorNetworkFilter()


    }

    private fun monitorNetworkFilter() {

        mViewModel.filter.observe(this) {

            parseState(it, {


                /**
                 *  ???????????????
                 */
                //       ??????
                setBrandAdapter(it)

                //       ??????
                setDistanceAdapter()

                //      ?????????
                setHallType(it)

                //       ??????
                setWholeCity(it)


            }, {

                showErrorMessage(it)
            })
        }


    }

    private fun monitorNetworkCinemaList() {

        mViewModel.cinemaList.observe(this) {

            parseState(it,
                {

                    movieTheatreAdapter.setList(it.data.cinemas)


                }, {
                    showErrorMessage(it)
                })


        }


    }

    private fun monitorIntentParamCityId() {

        mViewModel.cityId.observe(this) {

            if (it != 0) {




                /**
                 *   ??????????????????,?????????????????????
                 */
                setFilterCondition()


                /**
                 * @see recyclerView
                 */
                initRv()


            } else {

                it.toString().loge(javaClass.simpleName + ".Intent.params.cityId")


            }

        }

    }

    override fun layoutId(): Int {
        return R.layout.fragment_movie_theatre
    }

    override fun initView(savedInstanceState: Bundle?) {

        /**
         *       ?????????????????????
         */
        registerCityPickerChangedReceiver()


        /**
         *  ?????????????????????id
         * @see mViewModel.cityId
         * @see SharedPreferences
         */
        initCityId()


        /**
         * ?????????targetView?????? ????????????
         */
        initTargetViewOffY()


        /**
         * ????????????PopupWindow
         */
        initHeaderChildViewClickEvent()

    }

    private fun initCityId() {


        val sharedPreferences = mActivity.getSharedPreferences("CityDataInfo", Context.MODE_PRIVATE)
        mViewModel.cityId.value = sharedPreferences.getInt(Constant.KEY_CITYCODE, 0)


    }

    private fun initHeaderChildViewClickEvent() {
        view_header = LayoutInflater.from(mActivity)
            .inflate(R.layout.layout_header, null, false)


        //        ???Adapter???????????????
        //        movieTheatreAdapter.addHeaderView(view_header)


        tv_brand.setOnClickListener(this)
        tv_whole_city.setOnClickListener(this)
        tv_short_distance.setOnClickListener(this)
        tv_choose.setOnClickListener(this)

    }


    private fun setWholeCity(filter: Filter) {


        //                ??????
        val subItemXList = filter.data.district.subItems
        subItemXList[0].isSelect = true


        //            ??????

        subItemXXXXXList = filter.data.subway.subItems as MutableList<Filter.SubItemXXXXX>
        subItemXXXXXList[0].isSelect = true

        /**
         *  ???????????????
         * @see cityAreaParentAdapter
         */
        cityAreaParentAdapter = CityAreaParentAdapter(subItemXList as MutableList<Filter.SubItemX>)

        /**
         *   ????????????
         */
        view_whole_city =
            LayoutInflater.from(mActivity).inflate(R.layout.popup_window_city_area, null)

        val recyclerView_parent =
            view_whole_city.findViewById<RecyclerView>(R.id.recyclerView_parent)
        val recyclerView_sub =
            view_whole_city.findViewById<RecyclerView>(R.id.recyclerView_sub)


        /**
         *  ??????CommonTabLayout
         */

        setTabLayout(recyclerView_parent, recyclerView_sub)


        /**
         *   ?????????
         *   @see recyclerView_parent   ???View
         *
         *   ????????????
         *   @see recyclerView_sub   ???view
         */
        setPreviewLoadAdapter(
            recyclerView_parent,
            recyclerView_sub

        )


    }

    private fun setTabLayout(recyclerviewParent: RecyclerView, recyclerviewSub: RecyclerView) {

        val tabLayout = view_whole_city.findViewById<CommonTabLayout>(R.id.tabLayout)
        val listOf = arrayListOf<CustomTabEntity>(TabEntity("??????"), TabEntity("?????????"))
        tabLayout.setTabData(listOf)


        /**
         *    tabLayout  ????????????
         *
         */
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

                if (position == 0) {

                    subItemXXXXXXList.clear()

                    //  ??????tab??????0????????????
                    initTab0Event(recyclerviewParent, recyclerviewSub)

                } else {

                    subItemXXList.clear()
                    //    ??????tab??????1????????????
                    initTab1Event(recyclerviewParent, recyclerviewSub)


                }


            }

            override fun onTabReselect(position: Int) {
            }
        })


    }

    private fun initTab1Event(recyclerviewParent: RecyclerView, recyclerviewSub: RecyclerView) {

        //?????????subwayParentAdapter

        subwayParentAdapter = SubwayParentAdapter(subItemXXXXXList)


        /**
         *  ???????????????
         *  @see recyclerviewParent
         *  @see  subwayParentAdapter
         */
        recyclerviewParent.loadRecyclerAdapterByLinearLayoutManager(subwayParentAdapter, mActivity)


        /**
         *   item????????????
         */

        subwayParentAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            /**
             *   ??????item ??????,????????????????????????
             *   @see offset
             */
            mViewModel.offset = 0

            /**
             *
             *    ????????????
             */
            if (mViewModel.prePositionSubwayParent == currentPosition) {

                subItemXXXXXList[currentPosition].isSelect = true

            } else {

                subItemXXXXXList[currentPosition].isSelect = true
                subItemXXXXXList[mViewModel.prePositionSubwayParent].isSelect = false

            }


            //            ??????adapter ??????position??????

            val subItemX = subItemXXXXXList[currentPosition]   // ????????????   ??????1??????

            //              ????????????
            mViewModel.lineId = subItemX.id


            /**
             *  ?????????
             *  @see subItemXXXXXXList
             */
            subItemXXXXXXList = mutableListOf()
            subItemXXXXXXList.addAll(subItemX.subItems)  //???????????????????????????????????????   ????????????


            //           ????????????
            "${mViewModel.lineId}".logi("lineId")

            //?????????????????????currentPosition
            mViewModel.prePositionSubwayParent = currentPosition

            //    ?????? cityAreaParentAdapter ??????
            subwayParentAdapter.notifyDataSetChanged()


            /**
             *   ????????????subAdapter
             *   @see recyclerviewSub
             *   @see SubwaySubAdapter
             */
            initSubwaySubAdapter(
                subItemXXXXXXList,
                recyclerviewSub
            )


        }


    }

    private fun initSubwaySubAdapter(
        mutableList: MutableList<Filter.SubItemXXXXXX>,
        recyclerviewSub: RecyclerView
    ) {


        val subwaySubAdapter = SubwaySubAdapter(mutableList)

        /**
         *  ???????????????
         *  @see subwaySubAdapter
         *  @see recyclerviewSub
         */

        recyclerviewSub.loadRecyclerAdapterByLinearLayoutManager(subwaySubAdapter, mActivity)

        /**
         *   item ????????????
         */


        subwaySubAdapter.setOnItemClickListener { adapter, view, position ->

            /**
             *   ??????item ??????,????????????????????????
             *   @see offset
             */
            mViewModel.offset = 0

            //     ????????????
            val subItemXXXXXX = mutableList[position]

            if (position == 0) {
                mViewModel.stationIsClick = true
                mViewModel.stationId = subItemXXXXXX.id

            } else {
                mViewModel.stationIsClick = false
                mViewModel.stationId = subItemXXXXXX.id
            }

            //     ??????  header   tv_whole_city
            tv_whole_city.text = subItemXXXXXX.name

            //           ????????????
            "${mViewModel.stationId}".logi("stationId")


            /**
             *   ???????????? popupWindow
             */
            popupWindowManagerWholeCity.dismiss()

            tv_whole_city.setTextColor(resources.getColor(R.color.defaultText_color))

            /**
             *      ??????????????????????????????
             */

            mViewModel.getCinemaListBySubway(
                mViewModel.offset,
                mViewModel.lng,
                mViewModel.utm_medium,
                mViewModel.hallType,
                mViewModel.lineId,
                mViewModel.cityId.value,
                mViewModel.sort,
                mViewModel.utm_term,
                mViewModel.brandId,
                mViewModel.limit,
                mViewModel.serviceId,
                mViewModel.channelId,
                mViewModel.lat,
                mViewModel.stationId
            )

        }


    }

    private fun initTab0Event(recyclerviewParent: RecyclerView, recyclerviewSub: RecyclerView) {
        setPreviewLoadAdapter(recyclerviewParent, recyclerviewSub)

    }

    /**
     *
     * @param recyclerviewParent RecyclerView
     * @param recyclerviewSub RecyclerView
     */
    private fun setPreviewLoadAdapter(
        recyclerviewParent: RecyclerView,
        recyclerviewSub: RecyclerView
    ) {


        /**
         *   ??????item ??????,????????????????????????
         *   @see offset
         */
        mViewModel.offset = 0


        /**
         *  ???????????????
         *  @see cityAreaParentAdapter
         *  @see recyclerviewParent
         */

        recyclerviewParent.loadRecyclerAdapterByLinearLayoutManager(
            cityAreaParentAdapter,
            mActivity
        )

        /**
         *   item  ????????????
         */
        cityAreaParentAdapter.setOnItemClickListener { adapter, view, currentPosition ->

            val subItemXList = adapter.data as MutableList<Filter.SubItemX>

            /**
             *
             *    ????????????
             */
            if (mViewModel.prePositionCityAreaParent == currentPosition) {

                subItemXList[currentPosition].isSelect = true

            } else {

                subItemXList[currentPosition].isSelect = true
                subItemXList[mViewModel.prePositionCityAreaParent].isSelect = false

            }


            //            ??????adapter ??????position??????

            val subItemX = subItemXList[currentPosition]   // ??????   ???????????????


            //??????Item??????,????????????????????????

            subItemXXList.clear()
            //???????????????????????????    ?????????
            subItemXXList.addAll(subItemX.subItems)

            // ????????????
            mViewModel.districtId = subItemX.id

            //  ????????????
            "${mViewModel.districtId}".logi("districtId")

            //?????????????????????currentPosition
            mViewModel.prePositionCityAreaParent = currentPosition

            //    ?????? cityAreaParentAdapter ??????
            cityAreaParentAdapter.notifyDataSetChanged()


            /**
             *   ????????????subAdapter
             *   @see recyclerviewSub
             *   @see cityAreaSubAdapter
             */
            initCityAreaSubAdapter(
                subItemXXList,
                recyclerviewSub
            )


        }


    }

    private fun initCityAreaSubAdapter(
        subItems: MutableList<Filter.SubItemXX>,
        recyclerviewSub: RecyclerView
    ) {

        "$subItems".logi("subItems")


        //        ?????????
        val cityAreaSubAdapter = CityAreaSubAdapter(subItems)


        /**
         *   ???????????????
         *  @see cityAreaSubAdapter
         *  @see recyclerviewSub
         */

        recyclerviewSub.loadRecyclerAdapterByLinearLayoutManager(cityAreaSubAdapter, mActivity)

        /**
         *    item ????????????
         */
        cityAreaSubAdapter.setOnItemClickListener { adapter, view, position ->

            /**
             *   ??????item ??????,????????????????????????
             *   @see offset
             */
            mViewModel.offset = 0


            //     ????????????
            val subItemXX = subItems[position]

            if (position == 0) {

                mViewModel.areaIsClick = true
                mViewModel.areaId = subItemXX.id

            } else {

                mViewModel.areaId = subItemXX.id
                mViewModel.areaIsClick = false
            }

            //     ??????  header   tv_whole_city
            tv_whole_city.text = subItemXX.name

            //           ????????????

            "${mViewModel.areaId}".logi("areaId")

            /**
             *   ???????????? popupWindow
             */
            popupWindowManagerWholeCity.dismiss()

            tv_whole_city.setTextColor(resources.getColor(R.color.defaultText_color))

            /**
             *  ??????????????????????????????
             */
            mViewModel.getCinemaListByDistrict(
                mViewModel.offset,
                mViewModel.lng,
                mViewModel.utm_medium,
                mViewModel.hallType,
                mViewModel.cityId.value,
                mViewModel.sort,
                mViewModel.utm_term,
                mViewModel.districtId,
                mViewModel.areaId,
                mViewModel.brandId,
                mViewModel.limit,
                mViewModel.serviceId,
                mViewModel.channelId,
                mViewModel.lat
            )

        }

    }

    private fun setHallType(filter: Filter) {


        view_hall_type =
            LayoutInflater.from(mActivity).inflate(R.layout.popup_window_hall_type, null)
        val tv_reset = view_hall_type.findViewById<TextView>(R.id.tv_reset)  //??????
        val tv_complete = view_hall_type.findViewById<TextView>(R.id.tv_complete) //??????

        /**
         *  ?????????
         *  @see HalfTypeServiceAdapter
         */
        initHalfTypeServiceAdapter(filter)

        /**
         *  ?????????
         *  @see HallTypeAdapter
         */
        initHallTypeAdapter(filter)


    }

    private fun initHallTypeAdapter(filter: Filter) {

        //              ?????????halfType

        val subItemXXXList = filter.data.hallType.subItems
        subItemXXXList[0].isSelect = true

        val recyclerView_hallType =
            view_hall_type.findViewById<RecyclerView>(R.id.recyclerView_hallType)
        halfTypeAdapter = HalfTypeAdapter(subItemXXXList as MutableList<Filter.SubItemXXX>)


        /**
         *  ??????????????? adapter
         *  @see halfTypeAdapter
         *  @see recyclerView_hallType
         */
        recyclerView_hallType.loadRecyclerAdapterByGridLayoutManager(halfTypeAdapter, mActivity, 4)

        /**
         *         item ????????????
         */
        halfTypeAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            /**
             *
             *    ????????????
             */
            if (mViewModel.prePositionHalfType == currentPosition) {

                subItemXXXList[currentPosition].isSelect = true
                mViewModel.selectPositionHalfType = currentPosition

            } else {

                subItemXXXList[currentPosition].isSelect = true
                subItemXXXList[mViewModel.prePositionHalfType].isSelect = false

                mViewModel.selectPositionHalfType = currentPosition
            }


            /**
             *  3  ???????????? brandId
             *  @see  currentPosition
             *  @see   adapter
             */
            //                ????????????adapter ??????currentPosition??????
            val subItemXXX = adapter.data[currentPosition] as Filter.SubItemXXX


            val id = subItemXXX.id    //????????????subItemXXXX.id
            mViewModel.hallType = id

            //           ????????????
            "${mViewModel.hallType}".logi("hallType")

            //????????????item position?????????
            mViewModel.prePositionHalfType = currentPosition

            //??????halfTypeAdapter??????
            halfTypeAdapter.notifyDataSetChanged()


        }

    }

    private fun initHalfTypeServiceAdapter(filter: Filter) {

        //                ??????Service

        val subItemXXXXList = filter.data.service.subItems
        subItemXXXXList[0].isSelect = true


        val recyclerView_service =
            view_hall_type.findViewById<RecyclerView>(R.id.recyclerView_service)
        halfTypeServiceAdapter =
            HalfTypeServiceAdapter(subItemXXXXList as MutableList<Filter.SubItemXXXX>)


        /**
         *  ??????????????? adapter
         *  @see  recyclerView_service
         *  @see halfTypeServiceAdapter
         */
        recyclerView_service.loadRecyclerAdapterByGridLayoutManager(
            halfTypeServiceAdapter,
            mActivity,
            3
        )

        /**
         *         item ????????????
         */

        halfTypeServiceAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            /**
             *
             *    ????????????
             */
            if (mViewModel.prePositionService == currentPosition) {

                subItemXXXXList[currentPosition].isSelect = true
                mViewModel.selectPositionService = currentPosition

            } else {


                subItemXXXXList[currentPosition].isSelect = true
                subItemXXXXList[mViewModel.prePositionService].isSelect = false
                mViewModel.selectPositionService = currentPosition

            }


            /**
             *  3  ???????????? serviceId
             *  @see  currentPosition
             *  @see   adapter
             */
            //                ????????????adapter ??????currentPosition??????
            val subItemXXXX = adapter.data[currentPosition] as Filter.SubItemXXXX


            val id = subItemXXXX.id    //????????????subItemXXXX.id
            mViewModel.serviceId = id

            //????????????
            "${mViewModel.serviceId}".logi("serviceId")

            //????????????item position????????????
            mViewModel.prePositionService = currentPosition

            //  ??????halfTypeServiceAdapter??????
            halfTypeServiceAdapter.notifyDataSetChanged()


        }

    }

    private fun setDistanceAdapter() {

        val mutableMapOf1 = mutableMapOf<String, String>("?????????".to("distance"))
        val mutableMapOf2 = mutableMapOf<String, String>("?????????".to("price"))

        val mutableListOf = mutableListOf<MutableMap<String, String>>(mutableMapOf1, mutableMapOf2)

        view_distance =
            LayoutInflater.from(mActivity).inflate(R.layout.popup_window_distance, null)
        val recyclerView = view_distance.findViewById<RecyclerView>(R.id.recyclerView)
        val distanceAdapter = DistanceAdapter(mutableListOf)

        /**
         *  ??????????????? adapter
         * @see  recyclerView
         * @see  distanceAdapter
         */

        recyclerView.loadRecyclerAdapterByLinearLayoutManager(distanceAdapter, mActivity)

        /**
         *   item ????????????
         */
        distanceAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            /**
             *   ??????item ??????,????????????????????????
             *   @see offset
             */
            mViewModel.offset = 0


            /**
             *
             *   1 ??????TextView
             */
            //  ????????????currentPosition#TextView
            val tv_name_current = adapter.getViewByPosition(
                currentPosition,
                R.id.tv_name
            ) as TextView

            //??????prePositionDistanceTextView
            val tv_Name_pre =
                adapter.getViewByPosition(mViewModel.prePositionDistance, R.id.tv_name) as TextView


            /**
             *
             *    ????????????
             */
            if (mViewModel.prePositionDistance == currentPosition) {

                setTextColorByAdapter(
                    tv_name_current,
                    tv_Name_pre,
                    true
                )
            } else {

                setTextColorByAdapter(
                    tv_name_current,
                    tv_Name_pre,
                    false
                )

            }


            /**
             *  3  ???????????? brandId
             *  @see  currentPosition
             *  @see   adapter
             */
            //                ????????????adapter ??????currentPosition??????
            val subItem = adapter.data[currentPosition] as MutableMap<String, String>



            for ((k, v) in subItem) {

                if (mViewModel.prePositionDistance == currentPosition) {

                    //  ??????  header    ttv_short_distance
                    tv_short_distance.text = k

                    mViewModel.sort = v

                } else {

                    tv_short_distance.text = k

                    mViewModel.sort = v


                }

            }

            //????????????

            "${mViewModel.sort}".logi("sort")

            //????????????item position?????????
            mViewModel.prePositionDistance = currentPosition

            //??????popupWindowManagerDistance
            popupWindowManagerDistance.dismiss()

            tv_short_distance.setTextColor(resources.getColor(R.color.defaultText_color))


            /**
             *   ??????????????????????????????
             */
            mViewModel.getCinemaListByNormal(
                mViewModel.utm_term,
                mViewModel.offset,
                mViewModel.lng,
                mViewModel.utm_medium,
                mViewModel.brandId,
                mViewModel.limit,
                mViewModel.hallType,
                mViewModel.cityId.value,
                mViewModel.sort,
                mViewModel.serviceId,
                mViewModel.channelId,
                mViewModel.lat
            )


        }


    }

    private fun setBrandAdapter(filter: Filter) {

        val subItemList = filter.data.brand.subItems
        subItemList[0].isSelect = true



        view_brand =
            LayoutInflater.from(mActivity).inflate(R.layout.popup_window_brand, null)
        val recyclerView = view_brand.findViewById<RecyclerView>(R.id.recyclerView)


        val brandAdapter = BrandAdapter(subItemList as MutableList<Filter.SubItem>)

        /**
         *  ???????????????
         * @see recyclerView
         * @see brandAdapter
         */
        recyclerView.loadRecyclerAdapterByLinearLayoutManager(brandAdapter, mActivity)


        /**
         * item ????????????
         */

        brandAdapter.setOnItemClickListener { adapter, view, currentPosition ->


            /**
             *   ??????item ??????,????????????????????????
             *   @see offset
             *   @see districtId
             */
            mViewModel.districtId = -1
            mViewModel.offset = 0

            /**
             *
             *    ????????????
             */

            if (mViewModel.prePositionBrand == currentPosition) {

                subItemList[currentPosition].isSelect = true

            } else {

                subItemList[currentPosition].isSelect = true
                subItemList[mViewModel.prePositionBrand].isSelect = false
                //

            }


            /**
             *  3  ???????????? brandId
             *  @see  currentPosition
             *  @see   adapter
             */
            //                ????????????adapter ??????currentPosition??????
            val subItem = adapter.data[currentPosition] as Filter.SubItem

            //              ????????????subItemXXXX.id
            val id = subItem.id
            mViewModel.brandId = id

            mViewModel.prePositionBrand = currentPosition    //????????????item position?????????

            //            ??????  header    tv_brand
            tv_brand.text = subItem.name

            //            ????????????brandAdapter??????

            brandAdapter.notifyDataSetChanged()
            //            ??????popupWindowManagerBrand

            //            ????????????
            "${mViewModel.brandId}".logi("brandId")
            popupWindowManagerBrand.dismiss()

            tv_brand.setTextColor(resources.getColor(R.color.defaultText_color))


            /**
             *  ??????????????????????????????
             */

            mViewModel.getCinemaListByNormal(
                mViewModel.utm_term,
                mViewModel.offset,
                mViewModel.lng,
                mViewModel.utm_medium,
                mViewModel.brandId,
                mViewModel.limit,
                mViewModel.hallType,
                mViewModel.cityId.value,
                mViewModel.sort,
                mViewModel.serviceId,
                mViewModel.channelId,
                mViewModel.lat
            )

        }
    }

    private fun initRv() {


        /**
         *  ??????????????? adapter
         *  @see recyclerView
         *  @see movieTheatreAdapter
         */

        mDatabind.recyclerView.loadRecyclerAdapterByLinearLayoutManager(
            movieTheatreAdapter,
            mActivity
        )

        /**
         *  ?????????????????????????????????
         */
        mViewModel.getCinemaListByNormal(
            mViewModel.utm_term,
            mViewModel.offset,
            mViewModel.lng,
            mViewModel.utm_medium,
            mViewModel.brandId,
            mViewModel.limit,
            mViewModel.hallType,
            mViewModel.cityId.value,
            mViewModel.sort,
            mViewModel.serviceId,
            mViewModel.channelId,
            mViewModel.lat
        )


    }

    private fun initTargetViewOffY() {


        /**
         * ??????????????????targetView?????? ????????????
         *
         * @param targetView ??????view
         * @param offX       x????????????
         * @param offY       y????????????
         *
         * ?????? dp
         * @see offY =   CinemaFragmente#R.id.rl_header ?????? + MovieTheatreFragment#R.layout.layout_header ??????
         *
         */

        mViewModel.offY = dip2px(mActivity, 139F)


    }

    private fun setFilterCondition() {


        mViewModel.getFilterCondition(mViewModel.cityId.value)
    }


    private fun registerCityPickerChangedReceiver() {

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.CITYPICKER_CHANGED")

        cityPickerChangedReceiver = CityPickerChangedReceiver()
        mActivity.registerReceiver(cityPickerChangedReceiver, intentFilter)


    }

    inner class CityPickerChangedReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            //TODO

            if (intent != null) {

                mViewModel.cityId.value = intent.getIntExtra("cityId", 0)


            }


        }


    }

    override fun onClick(v: View?) {


        when (v?.id) {
            R.id.tv_brand -> {


                val height = dip2px(mActivity, 600F)
                popupWindowManagerBrand = PopupWindowManager.getInstance()
                    .init(
                        view_brand,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        height
                    )

                popupWindowManagerBrand.showAsDropDown(
                    view_header, 0, mViewModel.offY

                )


                if (mViewModel.tv_brandIsClick) {

                    setTextColor(
                        clickTextView = tv_brand,
                        unClickTextView1 = tv_whole_city,
                        unClickTextView2 = tv_short_distance,
                        unClickTextView3 = tv_choose
                    )


                } else {


                    popupWindowManagerBrand.dismiss()
                    tv_brand.setTextColor(resources.getColor(R.color.defaultText_color))


                }

                //????????????
                mViewModel.tv_brandIsClick = !mViewModel.tv_brandIsClick

            }
            R.id.tv_whole_city -> {

                val height = dip2px(mActivity, 600F)
                popupWindowManagerWholeCity = PopupWindowManager.getInstance()
                    .init(
                        view_whole_city,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        height
                    )
                popupWindowManagerWholeCity.showAsDropDown(view_header, 0, mViewModel.offY)

                if (mViewModel.tv_whole_cityIsClick) {

                    setTextColor(
                        clickTextView = tv_whole_city,
                        unClickTextView1 = tv_brand,
                        unClickTextView2 = tv_short_distance,
                        unClickTextView3 = tv_choose
                    )


                } else {

                    popupWindowManagerWholeCity.dismiss()
                    tv_whole_city.setTextColor(resources.getColor(R.color.defaultText_color))


                }

                mViewModel.tv_whole_cityIsClick = !mViewModel.tv_whole_cityIsClick

            }
            R.id.tv_short_distance -> {


                val height = dip2px(mActivity, 100F)

                popupWindowManagerDistance = PopupWindowManager.getInstance().init(
                    view_distance, ViewGroup.LayoutParams.MATCH_PARENT,
                    height
                )

                popupWindowManagerDistance.showAsDropDown(view_header, 0, mViewModel.offY)


                if (mViewModel.tv_short_distanceIsClick) {


                    setTextColor(
                        clickTextView = tv_short_distance,
                        unClickTextView1 = tv_brand,
                        unClickTextView2 = tv_whole_city,
                        unClickTextView3 = tv_choose
                    )


                } else {

                    popupWindowManagerDistance.dismiss()
                    tv_short_distance.setTextColor(resources.getColor(R.color.defaultText_color))

                }

                mViewModel.tv_short_distanceIsClick = !mViewModel.tv_short_distanceIsClick


            }
            else -> {


                val height = dip2px(mActivity, 500F)
                popupWindowManagerHalType = PopupWindowManager.getInstance()
                    .init(
                        view_hall_type,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        height
                    )
                popupWindowManagerHalType.showAsDropDown(view_header, 0, mViewModel.offY)


                if (mViewModel.tv_chooseIsClick) {

                    setTextColor(
                        clickTextView = tv_choose,
                        unClickTextView1 = tv_brand,
                        unClickTextView2 = tv_whole_city,
                        unClickTextView3 = tv_short_distance
                    )


                } else {

                    popupWindowManagerHalType.dismiss()
                    tv_choose.setTextColor(resources.getColor(R.color.defaultText_color))

                }


                mViewModel.tv_chooseIsClick = !mViewModel.tv_chooseIsClick


            }


        }


    }

    /**
     *
     * @param clickTextView TextView
     * @param unClickTextView1 TextView
     * @param unClickTextView2 TextView
     * @param unClickTextView3 TextView
     */
    private fun setTextColor(
        clickTextView: TextView,
        unClickTextView1: TextView,
        unClickTextView2: TextView,
        unClickTextView3: TextView
    ) {

        clickTextView.setTextColor(resources.getColor(R.color.colorPrimary))
        unClickTextView1.setTextColor(resources.getColor(R.color.defaultText_color))
        unClickTextView2.setTextColor(resources.getColor(R.color.defaultText_color))
        unClickTextView3.setTextColor(resources.getColor(R.color.defaultText_color))
    }

    /**
     *
     * @param tvNameCurrent TextView
     * @param tvNamePre TextView
     * @param isShow Boolean
     */
    private fun setTextColorByAdapter(
        tvNameCurrent: TextView,
        tvNamePre: TextView,
        isShow: Boolean
    ) {


        if (isShow) {

            tvNameCurrent.setTextColor(resources.getColor(R.color.colorPrimary))

        } else {

            tvNamePre.setTextColor(resources.getColor(R.color.color_66666))
            tvNameCurrent.setTextColor(resources.getColor(R.color.colorPrimary))


        }


    }


}