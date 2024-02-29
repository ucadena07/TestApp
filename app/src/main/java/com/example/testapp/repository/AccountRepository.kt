package com.example.testapp.repository

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.network.ApiResponse
import com.example.testapp.network.AppApi
import javax.inject.Inject

class AccountRepository @Inject constructor(private val api: AppApi) {
    suspend fun login(authRequest: AuthRequest) : ApiResponse<AuthResponse?>{
        return try {
             api.login(authRequest)
        } catch (e: Exception) {
             ApiResponse(result = null, errorMessages = listOf(e.localizedMessage), isSuccess = false)
        }
    }
}