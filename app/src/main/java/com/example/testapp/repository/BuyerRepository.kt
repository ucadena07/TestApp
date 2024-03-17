package com.example.testapp.repository

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.model.Buyer
import com.example.testapp.network.ApiResponse
import com.example.testapp.network.AppApi
import javax.inject.Inject

class BuyerRepository @Inject constructor(private val api: AppApi) {
    suspend fun getBuyers() : ApiResponse<List<Buyer>?> {
        return try {
            val data = api.getBuyers()
            if(data.result  == null){
                data.result = emptyList()
            }
            return  data
        } catch (e: Exception) {
            ApiResponse(result = null, errorMessages = listOf(e.localizedMessage), isSuccess = false)
        }
    }
}