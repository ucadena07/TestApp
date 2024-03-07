package com.example.testapp.network

import com.example.testapp.model.AuthRequest
import com.example.testapp.model.AuthResponse
import com.example.testapp.model.Buyer
import com.example.testapp.network.httpServices.IAccountHttpService
import com.example.testapp.network.httpServices.IBuyerHttpService
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AppApi : IBuyerHttpService, IAccountHttpService {



}