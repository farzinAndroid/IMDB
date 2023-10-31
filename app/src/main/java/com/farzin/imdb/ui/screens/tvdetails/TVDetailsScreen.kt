package com.farzin.imdb.ui.screens.tvdetails

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.mediaDetail.Genre
import com.farzin.imdb.ui.theme.appBackGround
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.viewmodel.HomeViewModel
import com.farzin.imdb.viewmodel.MediaDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TVDetailsScreen(
    tvId: Int,
    mediaDetailViewModel: MediaDetailViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var loading by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var numberOfEpisode by remember { mutableStateOf(0) }
    var runTime by remember { mutableStateOf<List<Int>>(emptyList()) }
    var startYear by remember { mutableStateOf("") }
    var endYear by remember { mutableStateOf("") }
    var picturePath by remember { mutableStateOf("") }
    var posterPath by remember { mutableStateOf("") }
    var overView by remember { mutableStateOf("") }
    var genres by remember { mutableStateOf<List<Genre>>(emptyList()) }
    var rating by remember { mutableStateOf(0.0) }
    var voteCount by remember { mutableStateOf(0) }


    //get media details
    LaunchedEffect(tvId) {
        mediaDetailViewModel.getTVDetails(tvId)

        mediaDetailViewModel.tvDetails.collectLatest { result ->
            when (result) {
                is NetworkResult.Success -> {
                    loading = false
                    name = result.data?.name ?: ""
                    date = result.data?.last_air_date ?: ""
                    status = result.data?.status ?: ""
                    numberOfEpisode = result.data?.number_of_episodes ?: 0
                    runTime = result.data?.episode_run_time ?: emptyList()
                    startYear = result.data?.first_air_date ?: ""
                    endYear = result.data?.last_air_date ?: ""
                    picturePath = result.data?.backdropPath ?: ""
                    overView = result.data?.overview ?: ""
                    posterPath = result.data?.poster_path ?: ""
                    genres = result.data?.genres ?: emptyList()
                    rating = result.data?.vote_average ?: 0.0
                    voteCount = result.data?.vote_count ?: 0
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("TAG", "error")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }

    var isInWatchList by remember { mutableStateOf(false) }
    var isRated by remember { mutableStateOf(false) }
    var userRating by remember { mutableStateOf(0.0) }


    LaunchedEffect(tvId) {
        homeViewModel.getWatchListTV()

        homeViewModel.watchListTV.collectLatest {result->
            when(result){
                is NetworkResult.Success->{
                    isInWatchList = result.data?.results?.any {
                        tvId == it.id
                    } ?: false
                }
                is NetworkResult.Error->{}
                is NetworkResult.Loading->{}
            }
        }

    }













    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.appBackGround)
    ) {

        stickyHeader {
            MediaDetailTopBarSection(name)
        }


        item {
            MediaDetailTitleSection(
                name = name,
                date = date,
                status = status,
                runTime = runTime,
                numberOfEpisode = numberOfEpisode,
                isMovie = false
            )
        }
        item {
            MediaPosterSection(
                picturePath = picturePath,
                startYear = startYear,
                endYear = endYear,
                isMovie = false,
                name = name
            )
        }
        item {

            if (overView.isEmpty())
                overView = stringResource(R.string.no_overView)

            MediaOverViewSection(
                genres = genres,
                overView = overView,
                posterPath = posterPath,
            )
        }
        item {
            MediaDetailAddToWatchListButton(
                buttonBackGround = MaterialTheme.colorScheme.imdbYellow,
                buttonBorderColor = MaterialTheme.colorScheme.imdbYellow,
                onClick = {
                    if (isInWatchList) {
                        scope.launch {
                            homeViewModel.addToWatchList(
                                AddToWatchListRequest(
                                    media_type = "tv",
                                    media_id = tvId,
                                    watchlist = false
                                )
                            )
                            isInWatchList = false
                        }
                    } else {
                        scope.launch {
                            homeViewModel.addToWatchList(
                                AddToWatchListRequest(
                                    media_type = "tv",
                                    media_id = tvId,
                                    watchlist = true
                                )
                            )
                            isInWatchList = true
                        }
                    }
                },
                isInWatchList = isInWatchList
            )

        }

        item {
            MediaRatingSection(
                rating = String.format("%.1f", rating),
                voteCount = voteCount,
                tvId = tvId,
            )
        }
    }


}