package com.example.testapp.network

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AppApi {
    @POST
    suspend fun login(@Body authRequest: AuthRequest) : AuthResponse
}