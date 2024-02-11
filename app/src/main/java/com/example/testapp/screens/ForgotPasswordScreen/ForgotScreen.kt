package com.example.testapp.screens.ForgotPasswordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.components.shared.HeadingTextComponent
import com.example.testapp.components.shared.NkButton
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.screens.loginScreen.LoginScreen

@Composable
fun ForgotScreen(navController: NavHostController?) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            HeadingTextComponent(value = "Password Reset")
            Text(text = "Enter the email address linked to your account and you will receive an email containing a link to reset your password.")
            Spacer(modifier = Modifier.heightIn(20.dp))
            NkTextField(label = "Email", icon = Icons.Default.Email)
            Spacer(modifier = Modifier.heightIn(20.dp))
            NkButton(value = "Reset Password")
            Spacer(modifier = Modifier.heightIn(20.dp))
            NkButton(value = "Back",color = MaterialTheme.colorScheme.secondary){
                navController?.popBackStack()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewForForgotScreen(){
    ForgotScreen(navController = null)
}