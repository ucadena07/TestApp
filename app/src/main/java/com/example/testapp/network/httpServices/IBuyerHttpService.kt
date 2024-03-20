package com.example.testapp.network.httpServices

import com.example.testapp.model.Buyer
import com.example.testapp.model.dto.BuyerDTO
import com.example.testapp.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface IBuyerHttpService {
    @GET("api/v1/Buyers/List")
    suspend fun getBuyers() : ApiResponse<List<Buyer>?>

    @GET("api/v1/Buyers/Get")
    suspend fun get(@Query("id") id: Int) : ApiResponse<BuyerDTO?>
}