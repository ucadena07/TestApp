package com.example.testapp.network

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapp.screens.homeScreen.HomeScreen
import com.example.testapp.screens.loginScreen.LoginScreen
import com.example.testapp.screens.splashScreen.SplashScreen

@Composable
fun ApplicationNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ApplicationScreens.SplashScreen.name){
        composable(ApplicationScreens.SplashScreen.name){
            SplashScreen(navController)
        }
        composable(ApplicationScreens.LoginScreen.name){
            LoginScreen(navController)
        }
        composable(ApplicationScreens.HomeScreen.name){
            HomeScreen(navController)
        }
    }
}