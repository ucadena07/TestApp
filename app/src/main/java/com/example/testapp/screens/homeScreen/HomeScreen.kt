package com.example.testapp.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.testapp.authentication.AuthState
import com.example.testapp.screens.loginScreen.formatDate
import com.example.testapp.screens.loginScreen.formatDateTime

@Composable
fun HomeScreen(navController: NavHostController) {
    Column {
        Text(text = "Welcome ${AuthState.getAuthDetails()?.name}")
        Text(text = "${AuthState.getAuthDetails()?.emailAddress}")
        Text(text = "Token exp: ${formatDate(AuthState.getAuthDetails()?.exp!!)} @ ${formatDateTime(AuthState.getAuthDetails()?.exp!!)} ")
    }


}