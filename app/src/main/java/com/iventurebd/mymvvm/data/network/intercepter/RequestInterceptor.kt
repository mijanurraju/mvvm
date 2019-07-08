package com.iventurebd.mymvvm.data.network.intercepter

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", "a7427bb89fd1f67e954cec30bd4d1cb8")
            .addQueryParameter("language", "en-US")
            .addQueryParameter("page", "1")
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
