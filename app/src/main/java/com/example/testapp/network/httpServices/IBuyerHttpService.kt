package com.example.testapp.network.httpServices

import com.example.testapp.model.Buyer
import com.example.testapp.network.ApiResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface IBuyerHttpService {
    @GET("api/v1/Buyers/List")
    suspend fun getBuyers() : ApiResponse<List<Buyer>?>
}