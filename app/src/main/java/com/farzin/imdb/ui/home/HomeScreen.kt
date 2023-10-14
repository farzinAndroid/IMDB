package com.farzin.imdb.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForWeek
import com.farzin.imdb.viewmodel.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {


    LaunchedEffect(true){
        getAllApiCalls(homeViewModel)
    }



    Home(homeViewModel)

}

@Composable
fun Home(homeViewModel: HomeViewModel) {


    var trendingTVShowsForWeek by remember { mutableStateOf<TrendingTVShowsForWeek>(TrendingTVShowsForWeek()) }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.trendingTVShowsForWeek.collectAsState()
    when(result){
        is NetworkResult.Success->{
            trendingTVShowsForWeek = result.data!!
            loading = false
        }
        is NetworkResult.Error->{
            loading = false
        }
        is NetworkResult.Loading->{
            loading = true
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ){

        items(trendingTVShowsForWeek.results){
            Text(text = it.name)
        }


    }

}




private fun getAllApiCalls(homeViewModel: HomeViewModel){
    homeViewModel.getAllApiCallsForHome()
}

