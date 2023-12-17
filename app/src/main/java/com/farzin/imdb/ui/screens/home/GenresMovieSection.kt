package com.farzin.imdb.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.TrendingMoviesForWeekResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.utils.Util
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GenresMovieSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {


    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    val movieGenre = Util(LocalContext.current).movieGenres
    val ctx = LocalContext.current


    //load movies based on those genre
    var movieLoading by remember { mutableStateOf(false) }
    var moviesBasedOnGenreList by remember {
        mutableStateOf<List<TrendingMoviesForWeekResult>>(
            emptyList()
        )
    }
    val moviesBasedOnGenreResult by homeViewModel.movieBasedOnGenre.collectAsState()
    when (moviesBasedOnGenreResult) {
        is NetworkResult.Success -> {
            moviesBasedOnGenreList = moviesBasedOnGenreResult.data?.results ?: emptyList()
            movieLoading = false
        }

        is NetworkResult.Error -> {
            movieLoading = false
        }

        is NetworkResult.Loading -> {
            movieLoading = true
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 20.dp)

        SectionTitleMaker(title = stringResource(R.string.top_genres))

        MySpacerHeight(height = 16.dp)



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.sectionContainerBackground
            ),
            shape = MaterialTheme.shapes.extraSmall
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {


                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    indicator = {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(it[selectedTabIndex])
                                .height(3.dp)
                                .background(MaterialTheme.colorScheme.imdbYellow)

                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    edgePadding = 0.dp,
                    containerColor = MaterialTheme.colorScheme.sectionContainerBackground,
                    contentColor = MaterialTheme.colorScheme.darkText,
                    divider = {
                        Divider(color = Color.Transparent)
                    },
                    tabs = {
                        movieGenre.forEachIndexed { index, genre ->

                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = {
                                    selectedTabIndex = index
                                },
                                content = {

                                    GenreCard(
                                        genreTitle = genre.name,
                                        onClick = {
                                            selectedTabIndex = index
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }
                    }
                )

                val selectedGenre = movieGenre.getOrNull(selectedTabIndex)
                if (selectedGenre != null) {
                    val genreId = selectedGenre.id


                    LaunchedEffect(selectedTabIndex) {
                        getMovieBasedOnGenre(homeViewModel, genreId.toString())
                    }
                }

                LazyRow(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    items(moviesBasedOnGenreList) { item ->
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
                                    Toast.makeText(ctx,ctx.getString(R.string.added_to_watchList),
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun getMovieBasedOnGenre(homeViewModel: HomeViewModel, genre: String) {
    homeViewModel.getMoviesBasedOnGenre(genre)
}