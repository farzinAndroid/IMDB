package com.farzin.imdb.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.TrendingTVShowsForDayResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.addBackground
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MyLoadingFullScreen
import com.farzin.imdb.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingTVShowsForDaySection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {


    val scope = rememberCoroutineScope()

    var trendingTVShowsForDay by remember {
        mutableStateOf<List<TrendingTVShowsForDayResult>>(
            emptyList()
        )
    }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.trendingTVShowsForDay.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            trendingTVShowsForDay = result.data?.results ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (loading){
        MyLoadingFullScreen(
            modifier = Modifier.fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp)
        )
    }else{
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

                var id by remember {
                    mutableStateOf(0)
                }


                var overView by remember {
                    mutableStateOf("")
                }


                Box {

                    HorizontalPager(
                        count = trendingTVShowsForDay.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                    ) { index ->
                        backdropPath = trendingTVShowsForDay[index].backdrop_path
                        posterPath = trendingTVShowsForDay[index].poster_path ?: ""
                        title = trendingTVShowsForDay[index].name
                        val voteNumber = trendingTVShowsForDay[index].vote_average
                        voteAvg = String.format("%.1f", voteNumber)
                        id = trendingTVShowsForDay[index].id
                        overView = trendingTVShowsForDay[index].overview

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate(
                                        Screens.TVDetails.route + "?id=${id}"
                                    )
                                },
                        ) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = ImageHelper.appendImage(backdropPath))
                                    .scale(Scale.FIT)
                                    .build()
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
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
                                PosterImage(
                                    backDropPath = posterPath,
                                    title = title,
                                    votesAverage = voteAvg,
                                    onClick = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = id,
                                                media_type = "tv",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(100)
                                            homeViewModel.getWatchListTV()
                                        }
                                    }
                                )
                            }

                        }
                    }

                }


                val isDraggedState by pagerState.interactionSource.collectIsDraggedAsState()
                LaunchedEffect(isDraggedState) {
                    snapshotFlow { isDraggedState }
                        .filter { !isDraggedState }
                        .collectLatest { isDragged ->
                            while (true) {
                                delay(6000)
                                var newPosition = pagerState.currentPage + 1
                                if (newPosition > trendingTVShowsForDay.size - 1) newPosition = 0
                                pagerState.animateScrollToPage(newPosition)
                            }
                        }
                }


            }
        }
    }



}