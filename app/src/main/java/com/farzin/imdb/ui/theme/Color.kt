package com.farzin.imdb.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.selectedColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFf3ce13) else Color(0xFF000000)

val ColorScheme.appBackGround: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1F1F1F) else Color(0xFFFFFFFF)


val ColorScheme.normalText: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFC9C9C9) else Color(0xFF000000)

val ColorScheme.imdbYellow: Color
    @Composable
    get() = Color(0xFFf3ce13 )