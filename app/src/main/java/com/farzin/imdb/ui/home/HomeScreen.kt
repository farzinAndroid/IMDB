package com.farzin.imdb.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.ui.theme.appBackGround
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {


    LaunchedEffect(true) {
        getAllApiCalls(homeViewModel)
    }

    Home(homeViewModel, navController)

}

@Composable
fun Home(homeViewModel: HomeViewModel, navController: NavController) {


    SwipeRefreshSection(homeViewModel = homeViewModel, navController = navController)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeRefreshSection(homeViewModel: HomeViewModel, navController: NavController) {

    val onRefresh = rememberCoroutineScope()
    val swipeRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = {
        onRefresh.launch {

            getAllApiCalls(homeViewModel)
            Log.e("TAG", "Refresh")
        }
    })

    Box(
        Modifier.pullRefresh(swipeRefreshState)
    ) {



        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .background(MaterialTheme.colorScheme.appBackGround)

        ) {

            item { TrendingTVShowsForDaySection() }
            item { WhatToWatchSection(navController) }
            item { PopularTVSection() }
            item { WatchListTVSection() }
            item { TrendingMoviesForWeekSection() }
            item { NowPlayingSection() }
            item { GenresMovieSection() }
            item { WatchListMovieSection() }


        }


        PullRefreshIndicator(
            refreshing = false,
            state = swipeRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )

    }


}


private fun getAllApiCalls(homeViewModel: HomeViewModel) {
    homeViewModel.getAllApiCallsForHome()
    homeViewModel.getWatchListTV()
    homeViewModel.getWatchListMovie()
}

