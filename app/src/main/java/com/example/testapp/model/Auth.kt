package com.example.testapp.model

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.annotations.MinLength
import me.naingaungluu.formconductor.annotations.Optional

@Form
data class AuthRequest(
    @EmailAddress
    val email: String = "",
    @MinLength(1)
    val password: String = ""
)

data class ForgotRequest(
    val email: String,
)

