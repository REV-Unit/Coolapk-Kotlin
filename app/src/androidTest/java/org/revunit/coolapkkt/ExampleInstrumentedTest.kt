package org.revunit.coolapkkt

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.revunit.coolapkkt.network.Client

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("moe.peanutmelonseedbigalmond.fkcoolapk", appContext.packageName)
    }

    @Test
    fun testMain(){
        runBlocking {
            val data=Client.getPicDataList("酷图",1)
            println(data)
        }
    }
}