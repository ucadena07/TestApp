package com.example.testapp.authentication

data class UserClaims(
    val name: String?,
    val emailAddress: String?,
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val environment: String?,
    val siteUserId: Int?,
    val role: List<String>?,
    val exp : Int?,
    var token: String? = null
    )
