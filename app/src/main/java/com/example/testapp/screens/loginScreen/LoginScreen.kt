package com.example.testapp.screens.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.testapp.network.ApplicationScreens

@Composable
fun LoginScreen(navController: NavHostController) {
    Column {
        Text(text = "Login Screen")
        Button(onClick = { navController.navigate(ApplicationScreens.HomeScreen.name) }) {
            Text(text = "Navigate Home")
        }
    }

}