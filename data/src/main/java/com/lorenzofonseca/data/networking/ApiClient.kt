package com.lorenzofonseca.data.networking

import com.lorenzofonseca.data.util.Constants.API_KEY
import com.lorenzofonseca.data.util.Constants.HOST_URL
import com.lorenzofonseca.data.util.Constants.TIMEOUT_SECONDS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null

    private val client: Retrofit
        get() {
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                httpClient.callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

                httpClient.addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {

                        var request = chain.request()
                        val url =
                            request.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
                        request = request.newBuilder().url(url).build()
                        return chain.proceed(request)
                    }
                })

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)

                retrofit = Retrofit.Builder()
                    .baseUrl(HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonUtil.gsonDefault))
                    .client(httpClient.build())
                    .build()
            }
            return retrofit as Retrofit
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return client.create(serviceClass)
    }
}