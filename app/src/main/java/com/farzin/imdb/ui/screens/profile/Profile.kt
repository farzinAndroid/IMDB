package com.farzin.imdb.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.WatchListMovieSection
import com.farzin.imdb.ui.screens.home.WatchListTVSection
import com.farzin.imdb.viewmodel.HomeViewModel

@Composable
fun Profile(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(true){
        getWatchLists(homeViewModel)
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ){
        item { ProfileTopBar {navController.navigate(Screens.Settings.route)} }
        item { WatchListTVSection(navController=navController) }
        item { WatchListMovieSection(navController=navController) }
    }






}


private fun getWatchLists(homeViewModel: HomeViewModel){
    homeViewModel.getWatchListTV()
    homeViewModel.getWatchListMovie()
}