package org.revunit.coolapkkt.data

import com.chad.library.adapter.base.entity.SectionEntity
import org.revunit.coolapkkt.BuildConfig
import org.revunit.coolapkkt.network.data.response.PicIndexData

class MainPageCoolPicCategory(
    val entryData: PicIndexData.DataBean.EntitiesData?,
    val headerString: String?
) : SectionEntity {
    override val isHeader: Boolean
        get() = headerString != null

    init {
        if (BuildConfig.DEBUG && !(entryData != null || headerString != null)) {
            error("Assertion failed")
        }
    }
}