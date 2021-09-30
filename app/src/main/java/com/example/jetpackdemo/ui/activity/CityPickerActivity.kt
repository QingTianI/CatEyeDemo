package com.example.jetpackdemo.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.observe
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.BaseActivity
import com.example.jetpackdemo.data.model.Constant
import com.example.jetpackdemo.data.model.bean.PickCity
import com.example.jetpackdemo.databinding.ActivityCityPickerBinding
import com.example.jetpackdemo.util.showErrorMessage
import com.example.jetpackdemo.viewmodel.activity.CityPickerViewModel
import com.zaaach.citypicker.CityPicker
import com.zaaach.citypicker.adapter.OnPickListener
import com.zaaach.citypicker.model.City
import me.hgj.jetpackmvvm.ext.parseState
import kotlin.concurrent.thread

class CityPickerActivity : BaseActivity<CityPickerViewModel, ActivityCityPickerBinding>() {
    override fun layoutId(): Int {


        return R.layout.activity_city_picker
    }

    override fun createObserver() {

        mViewModel.cityLiveData.observe(this) { resultState ->
            parseState(resultState, {

                loadCityCityPicker(it)

            }, {

                showErrorMessage(it)
            })

        }
    }


    private fun loadCityCityPicker(pickCityBean: PickCity) {


        CityPicker.from(this)
            .enableAnimation(false)
            .setAnimationStyle(R.style.DefaultCityPickerAnimation)
            .setLocatedCity(null)
            .setOnPickListener(object : OnPickListener {
                override fun onPick(position: Int, data: City) {


                    val cts = pickCityBean.cts   // 取出查询的数据

                    thread {

                        for (item in cts) {

                            if (data.name == item.nm) {


                                /**
                                 *  存储数据
                                 */
                                setSharedPreferencesData(item.id, item.nm)

                                /**
                                 *
                                 *     将数据返回给调用层
                                 */
                                val intent = Intent()
                                intent.putExtra("cityName", item.nm)
                                setResult(Activity.RESULT_OK, intent)


                                /**
                                 *   发送广播
                                 */

                                val cityPickedIntent =
                                    Intent("android.intent.action.CITYPICKER_CHANGED")
                                cityPickedIntent.putExtra("cityId", item.id)
                                sendBroadcast(cityPickedIntent)


                                /**
                                 *   关闭
                                 */
                                killMyself()
                                break

                            }


                        }


//
                    }


                }

                override fun onCancel() {

                    killMyself()

                }

                override fun onLocate() {
                    /**
                     *
                     *   定位
                     */

                }
            })
            .show()


    }

    fun setSharedPreferencesData(id: Int, nm: String) {
        /**
         *
         *      存储数据
         */
        getSharedPreferences(
            "CityDataInfo",
            Context.MODE_PRIVATE
        ).edit {
            putInt(Constant.KEY_CITYCODE, id)
            putString(Constant.KEY_CITYNAMEE, nm)

        }


    }

    fun killMyself() {
        finish()
    }

    override fun initTitle() {


    }

    override fun initData(savedInstanceState: Bundle?) {
        /**
         *  请求网络数据
         */
        mViewModel.getCity()

    }
}