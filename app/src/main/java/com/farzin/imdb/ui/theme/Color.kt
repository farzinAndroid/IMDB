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
    get() = if (isSystemInDarkTheme()) Color(0xFF313131) else Color(0xFFEEEEEE)


val ColorScheme.sectionContainerBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1F1F1F) else Color(0xFFFDFDFD)


val ColorScheme.darkText: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFC9C9C9) else Color(0xFF000000)

val ColorScheme.whiteBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)

val ColorScheme.imdbYellow: Color
    @Composable
    get() = Color(0xFFf3ce13 )


val ColorScheme.Cyan: Color
    @Composable
    get() = Color(0xFF00DBC3)


val ColorScheme.saveButtonBackground: Color
    @Composable
    get() = Color(0x9A7C7C7C)

val ColorScheme.addBackground: Color
    @Composable
    get() = Color(0xFFFFFFFF)


val ColorScheme.starBlue: Color
    @Composable
    get() = Color(0xB9005BFF)