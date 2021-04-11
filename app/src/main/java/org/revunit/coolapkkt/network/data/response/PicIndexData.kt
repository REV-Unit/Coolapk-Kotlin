package org.revunit.coolapkkt.network.data.response

data class PicIndexData(
    var status: Int = 0,
    var message: String? = null,
    var data: List<DataBean>? = null
) {

    /**
     * entityType : card
     * entityTemplate : configCard
     * title :
     * url :
     * entities : []
     * entityId : 14078
     * entityFixed : 0
     * pic :
     * lastupdate : 1576033555
     * extraData : {"flexList":"1","url":"\/feed\/coolPictureList?fragmentTemplate=flex","fragmentBackgroundColor":"fragmentTopDecoration","pageTitle":"\u9177\u56fe"}
     */
    data class DataBean(
        var entityType: String? = null,
        var entityTemplate: String? = null,
        var title: String? = null,
        var url: String? = null,
        var entityId: Int = 0,
        var entityFixed: Int = 0,
        var pic: String? = null,
        var lastupdate: Int = 0,
        var entities: List<EntitiesData>? = null,
        var extraData: String? = null
    ) {
        /**
         * entityType : picCategory",
         * title : #风景#",
         * url : /t/风景?type=8",
         * pic : http://image.coolapk.com/image/2017/1113/fengjing-0-o_1buq532i9100k7902fp1r951hinf-uid-10002@120x120.png"
         */
        data class EntitiesData(
            var entityType: String? = null,
            var title: String? = null,
            val url: String? = null,
            val pic: String? = null
        )
    }
}