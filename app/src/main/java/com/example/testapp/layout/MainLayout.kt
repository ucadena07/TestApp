package com.example.testapp.layout

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapp.authentication.AuthState
import com.example.testapp.components.shared.AppTooBar
import com.example.testapp.screens.loginScreen.formatDate
import com.example.testapp.screens.loginScreen.formatDateTime

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

