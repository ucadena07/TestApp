package com.example.testapp.model.dto

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.annotations.MinLength

@Form
data class AuthRequest(
    @EmailAddress
    val email: String = "",
    @MinLength(8)
    val password: String = ""
)

data class ForgotRequest(
    val email: String,
)

