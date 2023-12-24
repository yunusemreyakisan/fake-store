package com.yakisan.fakestore.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import com.yakisan.fakestore.ui.theme.Dark1

object Constant {
    const val BASE_URL = "https://fakestoreapi.com/"
}

//Getting screen focus manager
@Composable
fun getFocusManager(): FocusManager {
    return LocalFocusManager.current
}

//Getting theme
@Composable
fun getTheme(): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()) Dark1 else White
}

//Getting text theme
@Composable
fun getTextTheme(): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()) White else Dark1
}