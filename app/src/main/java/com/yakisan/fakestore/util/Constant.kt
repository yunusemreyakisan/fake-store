package com.yakisan.fakestore.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager

object Constant {
    const val BASE_URL = "http://10.0.2.2:8080/"
}

//Getting screen focus manager
@Composable
fun getFocusManager(): FocusManager {
    return LocalFocusManager.current
}

//Getting theme
@Composable
fun getTheme(): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()) DarkGray else White
}

//Getting text theme
@Composable
fun getTextTheme(): androidx.compose.ui.graphics.Color {
    return if (isSystemInDarkTheme()) White else DarkGray
}