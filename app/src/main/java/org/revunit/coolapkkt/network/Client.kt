package org.revunit.coolapkkt.network

import org.revunit.coolapkkt.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { it ->
            val newRequestBuilder = it.request().newBuilder()
            val headers = AppConfig.createHeader()
            headers.forEach {
                newRequestBuilder.removeHeader(it.key)
                    .addHeader(it.key, it.value)
            }
            return@addInterceptor it.proceed(newRequestBuilder.build())
        }.build()
    private val service = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.coolapk.com/v6/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

    suspend fun getPicDataList(
        title: String,
        page: Int,
    ) = service.getPicDataList(title, page)

    suspend fun getPicRecommendList(page: Int)= service.getRecommendData(page)
}