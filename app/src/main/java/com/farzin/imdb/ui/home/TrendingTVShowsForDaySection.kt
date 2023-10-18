package com.farzin.imdb.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.ui.theme.addBackground
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.Utils
import com.farzin.imdb.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingTVShowsForDaySection(homeViewModel: HomeViewModel = hiltViewModel()) {


    var trendingTVShowsForDay by remember {
        mutableStateOf<TrendingTVShowsForDay>(
            TrendingTVShowsForDay()
        )
    }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.trendingTVShowsForDay.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            trendingTVShowsForDay = result.data ?: TrendingTVShowsForDay()
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
            .fillMaxWidth()
            .height(350.dp)
            .background(MaterialTheme.colorScheme.sectionContainerBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            val pagerState = rememberPagerState()
            var backdropPath by remember {
                mutableStateOf("")
            }
            var posterPath by remember {
                mutableStateOf("")
            }
            var voteAvg by remember {
                mutableStateOf("")
            }

            var title by remember {
                mutableStateOf("")
            }


            Box {

                HorizontalPager(
                    count = trendingTVShowsForDay.results.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                ) { index ->
                    backdropPath = trendingTVShowsForDay.results[index].backdrop_path
                    posterPath = trendingTVShowsForDay.results[index].poster_path
                    title = trendingTVShowsForDay.results[index].name
                    val voteNumber = trendingTVShowsForDay.results[index].vote_average
                    voteAvg = String.format("%.1f",voteNumber)

                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = Utils.appendImage(backdropPath))
                                .scale(Scale.FIT)
                                .build()
                        )

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {

                            Image(
                                painter = painter, contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                contentScale = ContentScale.FillBounds
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    painter = painterResource(R.drawable.play),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.Center),
                                    tint = MaterialTheme.colorScheme.addBackground
                                )

                            }



                        }


                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            PosterImage(posterPath)
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {

                            SliderTitle(title,voteAvg)

                        }

                    }
                }

            }


        }
    }


}