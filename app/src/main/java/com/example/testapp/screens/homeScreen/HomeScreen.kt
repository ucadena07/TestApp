package com.example.testapp.screens.homeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Handyman
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeScreenViewModel) {
    val tabItems = listOf(
        TabItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon =  Icons.Filled.Home
        ),
        TabItem(
            title = "Browse",
            unselectedIcon = Icons.Outlined.ShoppingCart,
            selectedIcon =  Icons.Filled.ShoppingCart
        ),
        TabItem(
            title = "Utils",
            unselectedIcon = Icons.Outlined.Handyman,
            selectedIcon =  Icons.Filled.Handyman
        )
    )
    MainLayout(navController = navController, isHome = true) {
        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if(!pagerState.isScrollInProgress){
                selectedTabIndex = pagerState.currentPage
            }

        }
        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = { Text(text = tabItem.title) },
                        icon = {
                            Icon(
                                imageVector = if(index == selectedTabIndex){
                                                                      tabItem.selectedIcon
                                                                           }else tabItem.unselectedIcon,
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {index ->
                Box(modifier = Modifier.fillMaxSize()){
                    if(index == 0){
                        HomeContent(homeViewModel = homeViewModel, navController = navController)
                    }
                    if(index == 1){
                        Text(text = "Tab 2")
                    }
                    if(index == 2){
                        Text(text = "Tab 3")
                    }
                }
            }
        }
    }
}

//Column(modifier = Modifier.padding(3.dp)) {
//
//    Text(text = "Buyers", style = MaterialTheme.typography.headlineMedium)
//    //NkTextField(label = "Search")
//    Spacer(modifier = Modifier.height(10.dp))
//    LazyColumn {
//        items(homeViewModel.buyers.value!!){
//            BuyerCard(buyer = it){
//                navController.navigate("${ApplicationScreens.BuyerFormScreen}/${it.buyerID}")
//            }
//        }
//    }
//}

data class  TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)
@Composable
fun HomeContent(homeViewModel: HomeScreenViewModel, navController: NavController){
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