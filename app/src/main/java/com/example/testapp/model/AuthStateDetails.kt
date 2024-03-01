package com.example.testapp.model

data class AuthStateDetails(
    val firstName: String,
    val lastName: String,
    val token: String,
    val refreshToken: String,
    val tokenExp : String
)
