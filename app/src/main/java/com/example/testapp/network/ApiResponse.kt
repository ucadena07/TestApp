package com.example.testapp.network

data class ApiResponse<T>(val isSuccess: Boolean, val errorMessages: List<String>,val result: T)
