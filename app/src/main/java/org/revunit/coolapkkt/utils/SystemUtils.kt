package org.revunit.coolapkkt.utils

import android.os.Build
import android.text.Html
import android.util.Log
import java.util.*

object SystemUtils {
    private val randomAndroidId by lazy {
        val str = "123456789abcdef"
        var id = ""
        for (i in 1..16) {
            val rand = Random().nextInt(str.length)
            id += str[rand]
        }
        Log.i("SystemUtils", "AndroidId: $id")
        return@lazy id
    }

    fun getMacAddress() = "02:00:00:00:00:00"

    fun getImei() = "null"

    fun getImsi() = "null"

    fun getAndroidId() = randomAndroidId

    fun getUserAgent(str: String, i: Int): String {
        val str1 = System.getProperty("http.agent")
        val ua =
            Html.escapeHtml("$str1 (#Build; ${Build.BRAND}; ${Build.MODEL}; ${Build.DISPLAY}; ${Build.VERSION.RELEASE}) +CoolMarket/$str-$i")
        return ua
    }
}