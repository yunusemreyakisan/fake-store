package com.yakisan.fakestore.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen") //Splash Screen
    object WelcomeScreen : Screen("welcome_screen") //Welcome Screen
    object HomeScreen : Screen("home_screen") //Home Screen
    //Other Screens
}