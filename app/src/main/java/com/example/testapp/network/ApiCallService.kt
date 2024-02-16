package com.example.testapp.network

import me.naingaungluu.formconductor.composeui.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCallService {
    private val BASE_URL = "https://camposbrothersfarms-dev.netkinetix.com/"

    private val client = OkHttpClient.Builder()
    private var api: AppApi? = null

    private fun getApi() : AppApi{
        if(api == null){
            val client = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            if(BuildConfig.DEBUG){
                client.addInterceptor(logging)
            }
            client.addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .addHeader("user-agent", "android")
                    .build()
                chain.proceed(newRequest)
            }
            api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(ApiCallService.client.build())
                .build()
                .create(AppApi::class.java)
        }
        return api!!
    }
}