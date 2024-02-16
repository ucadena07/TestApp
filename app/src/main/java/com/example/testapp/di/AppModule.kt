package com.example.testapp.di

import com.example.testapp.network.AppApi
import com.example.testapp.utils.SD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppApi(): AppApi{
        return Retrofit.Builder()
            .baseUrl(SD.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }
}