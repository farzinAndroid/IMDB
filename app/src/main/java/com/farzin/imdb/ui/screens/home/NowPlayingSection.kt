package com.farzin.imdb.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.farzin.imdb.models.home.NowPlayingResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NowPlayingSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController:NavController
) {


    val scope = rememberCoroutineScope()

    var nowPlayingMoviesList by remember {
        mutableStateOf<List<NowPlayingResult>>(
            emptyList()
        )
    }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.nowPlaying.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            nowPlayingMoviesList = result.data?.results ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }






    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 20.dp)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
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

                SectionStickyHeader(stringResource(R.string.now_playing))

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    items(nowPlayingMoviesList){item->
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

                MySpacerHeight(height = 20.dp)
            }


        }

    }

}