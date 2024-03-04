package com.example.testapp.screens.homeScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.testapp.authentication.AuthState

@Composable
fun HomeScreen(navController: NavHostController) {
    Text(text = "Welcome ${AuthState.getAuthDetails()?.name}")
}