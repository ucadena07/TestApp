package com.example.testapp.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.testapp.authentication.AuthState
import com.example.testapp.network.AppApi
import com.example.testapp.utils.SD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.naingaungluu.formconductor.composeui.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppApi(): AppApi{

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
                .addHeader("Authorization", "bearer ${AuthState.getAuthDetails()?.token}")
                .build()

            //Log.d("API REQUEST BODY", request.body.toString())
            chain.proceed(newRequest)
        }



        return Retrofit.Builder()
            .baseUrl(SD.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(AppApi::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}