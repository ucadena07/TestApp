package com.example.testapp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp.screens.ForgotPasswordScreen.ForgotScreen
import com.example.testapp.screens.buyerScreen.BuyerFormScreen
import com.example.testapp.screens.homeScreen.HomeScreen
import com.example.testapp.screens.homeScreen.HomeScreenViewModel
import com.example.testapp.screens.loginScreen.LoginScreen
import com.example.testapp.screens.loginScreen.LoginScreenViewModel
import com.example.testapp.screens.splashScreen.SplashScreen

@Composable
fun ApplicationNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ApplicationScreens.SplashScreen.name){
        composable(ApplicationScreens.SplashScreen.name){
            SplashScreen(navController)
        }
        composable(ApplicationScreens.LoginScreen.name){
            BackHandler(true) {}
            val loginScreenViewModel = hiltViewModel<LoginScreenViewModel>()
            LoginScreen(navController,loginScreenViewModel)
        }
        composable(ApplicationScreens.ForgotScreen.name){
            BackHandler(true) {}
            ForgotScreen(navController)
        }
        composable(ApplicationScreens.HomeScreen.name){
            BackHandler(true) {}
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController, homeViewModel)
        }
        composable("${ApplicationScreens.BuyerFormScreen.name}/{id}", arguments = listOf(navArgument("id"){
            type = NavType.IntType
        })){
            it.arguments?.getInt("id").let {id ->
                BuyerFormScreen(id!!)
            }
        }
    }
}