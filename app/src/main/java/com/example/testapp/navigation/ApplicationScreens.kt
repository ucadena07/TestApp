package com.example.testapp.navigation

enum class ApplicationScreens {
    SplashScreen,
    HomeScreen,
    ForgotScreen,
    LoginScreen,
    BuyerFormScreen;

    companion object{
        fun fromRoute(route:String?) : ApplicationScreens
            = when(route?.substringBefore("/")){
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                ForgotScreen.name -> ForgotScreen
                HomeScreen.name -> HomeScreen
                BuyerFormScreen.name -> BuyerFormScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route not found.")
            }
    }

}