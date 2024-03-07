package com.example.testapp.network.httpServices

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.network.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IAccountHttpService {
    @POST("api/v1/Accounts/Login")
    suspend fun login(@Body authRequest: AuthRequest) : ApiResponse<AuthResponse?>
}