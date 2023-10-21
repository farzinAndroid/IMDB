package com.farzin.imdb.ui.home

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.Genre
import com.farzin.imdb.models.home.TrendingMoviesForWeekResult
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun GenresMovieSection(homeViewModel: HomeViewModel = hiltViewModel()) {


    var selectedTabIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    val genres = listOf(
        Genre(28, stringResource(R.string.action)),
        Genre(12, stringResource(R.string.adventure)),
        Genre(16, stringResource(R.string.animation)),
        Genre(35, stringResource(R.string.comedy)),
        Genre(80, stringResource(R.string.crime)),
        Genre(99, stringResource(R.string.documentary)),
        Genre(18, stringResource(R.string.drama)),
        Genre(10751, stringResource(R.string.family)),
        Genre(14, stringResource(R.string.fantasy)),
        Genre(36, stringResource(R.string.history)),
        Genre(27, stringResource(R.string.horror)),
        Genre(10402, stringResource(R.string.music)),
        Genre(9648, stringResource(R.string.mystery)),
        Genre(10749, stringResource(R.string.romance)),
        Genre(878, stringResource(R.string.science_fiction)),
        Genre(10770, stringResource(R.string.tv_movie)),
        Genre(53, stringResource(R.string.thriller)),
        Genre(10752, stringResource(R.string.war)),
        Genre(37, stringResource(R.string.western))
    )


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
                    contentColor = MaterialTheme.colorScheme.normalText,
                    divider = {
                        Divider(color = Color.Transparent)
                    }
                ) {

                    genres.take(6).forEachIndexed { index, genre ->

                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                            },
                            content = {

                                GenreCard(
                                    genreTitle = getGenreNamesBasedOnLang(genre),
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

                when (selectedTabIndex) {
                    0 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[0].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    1 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[1].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    2 -> {
                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[2].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    3 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[3].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    4 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[4].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    5 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[5].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListMovie()
                                        }
                                    }
                                )
                            }
                        }

                    }

                    6 -> {

                        LaunchedEffect(true) {
                            getMovieBasedOnGenre(homeViewModel, genres[6].id.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp)
                        ) {
                            items(moviesBasedOnGenreList) { item ->
                                MovieItem(
                                    item = item,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
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

private fun getPersianGenreName(genreId: Int): String {
    return when (genreId) {
        28 -> "اکشن"
        12 -> "ماجراجویی"
        16 -> "انیمیشن"
        35 -> "کمدی"
        80 -> "جنایی"
        99 -> "مستند"
        else -> genreId.toString()
    }
}

private fun getGenreNamesBasedOnLang(genre: Genre): String {
    return if (Locale.getDefault().language == "fa") {
        getPersianGenreName(genre.id)
    } else genre.name
}

private fun getMovieBasedOnGenre(homeViewModel: HomeViewModel, genre: String) {
    homeViewModel.getMoviesBasedOnGenre(genre)
}