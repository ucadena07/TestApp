package com.example.testapp.screens.homeScreen

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.authentication.AuthState
import com.example.testapp.components.shared.AppTooBar
import com.example.testapp.screens.loginScreen.formatDate
import com.example.testapp.screens.loginScreen.formatDateTime

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeScreenViewModel) {
    Scaffold(topBar = { AppTooBar(title = "Welcome ${AuthState.getAuthDetails()?.name}", showProfile = false, navController = navController) }) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column {
                Text(text = "Email: ${AuthState.getAuthDetails()?.emailAddress}")
                Text(text = "Phone: ${AuthState.getAuthDetails()?.phoneNumber}")
                Text(text = "Token exp: ${formatDate(AuthState.getAuthDetails()?.exp!!)} @ ${formatDateTime(AuthState.getAuthDetails()?.exp!!)} ")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Buyers ")
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(homeViewModel.buyers.value!!){
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)) {
                            Text(text = it.buyerName)
                        }
                    }
                }
            }


        }
    }
}