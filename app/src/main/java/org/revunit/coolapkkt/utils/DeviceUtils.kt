package org.revunit.coolapkkt.utils

import android.content.Context
import android.content.res.Configuration
import org.revunit.coolapkkt.CoolapkKotlinApplication

object DeviceUtils {
    /**
     * 判断是否平板设备
     * @return true:平板,false:手机
     */
    fun isTabletDevice(): Boolean {
        return CoolapkKotlinApplication.myApplicationContext.resources
            .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >=
                Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    /**
     * 判断是否是横屏
     * @param context
     * @return Boolean
     */
    fun isScreenOrientationLandscape(context: Context = CoolapkKotlinApplication.myApplicationContext): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}