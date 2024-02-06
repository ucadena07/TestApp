package com.example.testapp.network

enum class ApplicationScreens {
    SplashScreen,
    HomeScreen,
    LoginScreen;

    companion object{
        fun fromRoute(route:String?) : ApplicationScreens
            = when(route?.substringBefore("/")){
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                HomeScreen.name -> HomeScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route not found.")
            }
    }

}