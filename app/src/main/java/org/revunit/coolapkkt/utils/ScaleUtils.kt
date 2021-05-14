package org.revunit.coolapkkt.utils

import org.revunit.coolapkkt.CoolapkKotlinApplication


object ScaleUtils {
    /**
     * dp转px
     * @param dpValue Int
     * @return Int
     */
    fun dp2px(dpValue: Int): Int {
        val scale = CoolapkKotlinApplication.myApplicationContext.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px转dp
     * @param pxValue Int
     * @return Int
     */
    fun px2dp(pxValue: Int): Int {
        val scale = CoolapkKotlinApplication.myApplicationContext.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun getScreenWidthPixels(): Int =
        CoolapkKotlinApplication.myApplicationContext.resources.displayMetrics.widthPixels
}