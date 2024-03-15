package com.example.testapp.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.testapp.components.shared.AppTooBar

@Composable
fun MainLayout(navController: NavController,content: @Composable() () -> Unit){
    Scaffold(topBar = { AppTooBar(title = "NK App", showProfile = false, navController = navController) }) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}

