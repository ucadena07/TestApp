package com.example.testapp.screens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapp.authentication.AuthState
import com.example.testapp.components.buyers.BuyerCard
import com.example.testapp.components.shared.NkTextField
import com.example.testapp.layout.MainLayout
import com.example.testapp.model.Buyer
import com.example.testapp.navigation.ApplicationScreens
import com.example.testapp.utils.formatApiDateToTimestamp
import com.example.testapp.utils.formatDate
import com.example.testapp.utils.formatDateTime

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeScreenViewModel) {
    MainLayout(navController = navController, isHome = true) {
        Column(modifier = Modifier.padding(3.dp)) {
            
            Text(text = "Buyers", style = MaterialTheme.typography.headlineMedium)
            //NkTextField(label = "Search")
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(homeViewModel.buyers.value!!){
                  BuyerCard(buyer = it){
                      navController.navigate("${ApplicationScreens.BuyerFormScreen}/${it.buyerID}")
                  }
                }
            }
        }
    }
}

