package com.example.testapp.screens.accountDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.testapp.layout.MainLayout

@Composable
fun AccountDetailsScreen(navController: NavController) {
    MainLayout(navController = navController) {
        Column {
                Text(text = "Account Details")
        }
    }
}