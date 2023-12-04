package com.farzin.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.farzin.imdb.ui.screens.cast_detail.ImageScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageScreenViewModel @Inject constructor() : ViewModel() {

    var imageScreenState by mutableStateOf(ImageScreenState.IMAGE_LIST)

}