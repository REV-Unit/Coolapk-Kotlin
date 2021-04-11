package org.revunit.coolapkkt.utils

import android.util.Base64
import java.security.MessageDigest

object EncodeUtils {
    fun md5(str: String): String {
        val m = MessageDigest.getInstance("MD5")
        m.update(str.toByteArray())
        val array = m.digest()
        var result = ""
        array.forEach {
            result += Integer.toHexString(0x000000FF and it.toInt() or 0xFFFFFF00.toInt())
                .substring(6)
        }
        return result
    }

    fun base64(str: String): String {
        return Base64.encodeToString(str.toByteArray(), Base64.DEFAULT)
            .replace(Regex("\\r\\n|\\r|\\n"), "")
    }
}
