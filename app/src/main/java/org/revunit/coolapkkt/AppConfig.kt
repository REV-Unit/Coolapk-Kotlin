package org.revunit.coolapkkt

import android.os.Build
import org.revunit.coolapkkt.utils.EncodeUtils
import org.revunit.coolapkkt.utils.SystemUtils
import java.util.*

object AppConfig {
    private fun getXAppDevice(str: String) = EncodeUtils.base64(str)
        .replace("=", "")
        .reversed()

    private fun getAuthString(rawDeviceID: String): String {
        val time = (System.currentTimeMillis() / 1000).toInt()
        val timeHex = Integer.toHexString(time)
        val tMd5 = EncodeUtils.md5(time.toString())
        val deviceID = getDeviceID(rawDeviceID)
        val token =
            "token://com.coolapk.market/c67ef5943784d09750dcfbb31020f0ab?${tMd5}$${deviceID}&com.coolapk.market"
        val tokenMd5 = EncodeUtils.md5(EncodeUtils.base64(token))
        val result = "${tokenMd5}${deviceID}0x${timeHex}"
        return result
    }

    private fun getDeviceID(rawDeviceID: String) =
        UUID.nameUUIDFromBytes(rawDeviceID.toByteArray()).toString()

    fun createHeader(): Map<String, String> {
        val ua = SystemUtils.getUserAgent(Constants.appVersion, Constants.appCode)
        val token = getAuthString(SystemUtils.getAndroidId())
        val str = "${SystemUtils.getAndroidId()}; " +
                "${SystemUtils.getImei()}; " +
                "${SystemUtils.getImsi()}; " +
                "${SystemUtils.getMacAddress()}; " +
                "${Build.MANUFACTURER}; " +
                "${Build.BRAND}; " +
                Build.MODEL
        val xAppDevice = getXAppDevice(str)

        return mapOf(
            "User-Agent" to ua,
            "X-Requested-With" to "XMLHttpRequest",
            "X-Sdk-Int" to Build.VERSION.SDK_INT.toString(),
            "X-Sdk-Locale" to "zh-CN",
            "X-App-Id" to Constants.appName,
            "X-App-Token" to token,
            "X-Api-Version" to Constants.apiVersion,
            "X-App-Version" to Constants.appVersion,
            "X-App-Code" to Constants.appCode.toString(),
            "X-App-Device" to xAppDevice,
            "X-Dark-Mode" to "0",
            "Host" to Constants.host
        )
    }
}