package com.yakisan.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakisan.fakestore.ui.screens.HomeScreen
import com.yakisan.fakestore.ui.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController() //NavController Value
    //Host
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    )
    {
        //Splash Screen
        composable(route = Screen.SplashScreen.route)
        {
            SplashScreen(navController)
        }

        //Home Screen
        composable(route = Screen.HomeScreen.route)
        {
            HomeScreen(navController)
        }

    }
}