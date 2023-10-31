package com.farzin.imdb.ui.screens.service

import androidx.compose.ui.graphics.painter.Painter

data class ServiceModel(
    val name:String,
    val logo:Painter?,
    val id:Int,
    val isSelected:Boolean = false
)
