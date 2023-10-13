package com.farzin.imdb.navigation.bottomNavBar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedColor: Color,
    val deSelectedColor: Color,
    val icon : Painter
)
