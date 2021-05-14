package org.revunit.coolapkkt.network

import org.revunit.coolapkkt.network.data.response.PicIndexData
import org.revunit.coolapkkt.network.data.response.PicRecommendData
import org.revunit.coolapkkt.network.data.response.PostInfoData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    /**
     * 获取酷图首页的分类[风景/二次元/生活等等]
     * 和下面的分类[2K/平板等等]
     * @param title 类型，默认为"酷图"
     * @param page 分页
     */
    @GET("page/dataList")
    suspend fun getPicDataList(
        @Query("title") title: String,
        @Query("page") page: Int,
        @Query("url") url: String = "V9_HOME_TAB_COOLPIC"
    ): PicIndexData

    /**
     * 获取酷图推荐
     * @param page Int
     * @param title String
     * @param subTitle String
     * @param fragmentTemplate String
     * @param url String
     * @return PicRecommendData
     */
    @GET("page/dataList")
    suspend fun getRecommendData(
        @Query("page") page: Int,
        @Query("title") title: String = "",
        @Query("subtTitle") subTitle: String = "",
        @Query("fragmentTemplate") fragmentTemplate: String = "flex",
        @Query("url") url: String = "/feed/coolPictureList"
    ): PicRecommendData

    /**
     * 获取帖子详情内容
     * @param postId Long
     * @param rid String
     * @param fromApi String
     * @return PostInfoData
     */
    @GET("/v6/feed/detail")
    suspend fun getPostInfoData(
        @Query("id") postId: Long,
        @Query("rid") rid: String = "noticeId",
        @Query("fromApi") fromApi: String = ""
    ): PostInfoData
}