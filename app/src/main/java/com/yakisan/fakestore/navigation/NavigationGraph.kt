package com.yakisan.fakestore.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakisan.fakestore.ui.screens.HomeScreen
import com.yakisan.fakestore.ui.screens.SplashScreen
import com.yakisan.fakestore.ui.screens.WelcomeScreen

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


        //Welcome Screen
        composable(route = Screen.WelcomeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            })
        {
            WelcomeScreen(navController)
        }

        //Home Screen
        composable(route = Screen.HomeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            })
        {
            HomeScreen(navController)
        }

    }
}