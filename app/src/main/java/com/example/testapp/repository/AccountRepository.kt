package com.example.testapp.repository

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.network.AppApi
import javax.inject.Inject

class AccountRepository @Inject constructor(private val api: AppApi) {
    suspend fun login(authRequest: AuthRequest) : AuthResponse{
        return try {
             api.login(authRequest)
        } catch (e: Exception) {
             AuthResponse(reason = e.localizedMessage)
        }
    }
}