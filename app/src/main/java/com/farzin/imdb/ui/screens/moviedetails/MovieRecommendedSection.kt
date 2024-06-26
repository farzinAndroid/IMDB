package com.farzin.imdb.ui.screens.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.MovieResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.MovieItem
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.My3DotsLoading
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import com.farzin.imdb.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieRecommendedSection(
    mediaId: Int,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {

    LaunchedEffect(true) {
        movieDetailViewModel.getMovieRecommendation(mediaId)
    }

    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }
    var recommendedList by remember { mutableStateOf<List<MovieResult>>(emptyList()) }


    val result by movieDetailViewModel.movieRecommendation.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            recommendedList = result.data?.results ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (recommendedList.isNotEmpty()){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            MySpacerHeight(height = 24.dp)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
                shape = MaterialTheme.shapes.extraSmall
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {

                    SectionStickyHeader(stringResource(R.string.more_like_this))

                    MySpacerHeight(height = 8.dp)

                    if (loading){
                        My3DotsLoading(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }else{
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(recommendedList) { item ->
                                MovieItem(
                                    posterPath = item.poster_path ?: "",
                                    voteAverage = item.vote_average,
                                    name = item.title,
                                    releaseDate = item.release_date,
                                    onCardClicked = {
                                        navController.navigate(
                                            Screens.MovieDetails.route + "?id=${item.id}"
                                        )
                                    },
                                    onAddButtonClicked = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(200)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }
                    }

                }


            }

        }
    }

}