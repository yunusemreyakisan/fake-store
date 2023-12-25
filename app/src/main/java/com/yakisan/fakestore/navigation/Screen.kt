package com.yakisan.fakestore.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen") //Splash Screen
    object HomeScreen : Screen("home_screen") //Home Screen
    object ProductDetailScreen : Screen("product_detail_screen") //Product Detail Screen

    //Other Screens
}