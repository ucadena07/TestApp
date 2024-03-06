package com.example.testapp.network

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.model.Buyer
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AppApi {
    @POST("api/v1/Accounts/Login")
    suspend fun login(@Body authRequest: AuthRequest) : ApiResponse<AuthResponse?>

    @GET("api/v1/Buyers/List")
    suspend fun getBuyers() : ApiResponse<List<Buyer>?>
}