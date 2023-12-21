package com.yakisan.fakestore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yakisan.fakestore.R
import com.yakisan.fakestore.navigation.Screen
import com.yakisan.fakestore.ui.theme.FakeStoreTheme
import com.yakisan.fakestore.ui.theme.dimens
import com.yakisan.fakestore.util.getTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    //Splash to Home
    LaunchedEffect(true) {
        delay(1000) // 1 saniye gecikme -> HomeScreen
        navController.navigate(Screen.HomeScreen.route)
    }
    //Theme Control
    val theme = getTheme()

    FakeStoreTheme(
        statusBarColor = theme,
        navigationBarColor = theme
    ) {
        //Content
        Box(
            modifier = Modifier
                .background(color = theme)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(MaterialTheme.dimens.logoSize / 1.5f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Fake Store logo"
            )
        }
    }
}