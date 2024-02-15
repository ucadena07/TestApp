package com.example.testapp.network

import com.example.testapp.utils.ApiType

data class ApiRequest(val url: String, val apiType: ApiType, val data: Any)