package com.farzin.imdb.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun MySpacerWidth(width : Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun MySpacerHeight(height : Dp) {
    Spacer(modifier = Modifier.height(height))
}
