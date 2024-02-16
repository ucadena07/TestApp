package com.example.testapp.model

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.annotations.Optional
import java.util.Date

@Form
data class AuthRequest(
    @EmailAddress
    val email: String = "",
    @MinLength(1)
    val password: String = ""
)

data class AuthResponse(
    val token: String? =null,
    val tokenExpDate: Date?=null,
    val refreshToken: String?=null,
    val isSuccess: Boolean?=null,
    val reason: String?=null
)

data class ForgotRequest(
    val email: String,
)

