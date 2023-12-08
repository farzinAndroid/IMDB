package com.farzin.imdb.ui.screens.tvdetails

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.movieDetail.VideoResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.MovieDetailViewModel
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MediaVideoSection(
    mediaId: Int,
    mediaType: String,
    poster: String,
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    navController: NavController,
) {

    var videosList by remember { mutableStateOf<List<VideoResult>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val ctx = LocalContext.current

    if (mediaType == "tv") {
        LaunchedEffect(true) {
            tvDetailViewModel.getVideosForTV(mediaId)

            tvDetailViewModel.youtubeVideos.collectLatest { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        loading = false
                        videosList = result.data ?: emptyList()
                    }

                    is NetworkResult.Error -> {
                        loading = false
                    }

                    is NetworkResult.Loading -> {
                        loading = false
                    }
                }
            }
        }


    } else {
        LaunchedEffect(true) {
            movieDetailViewModel.getVideosForMovie(mediaId)

            movieDetailViewModel.youtubeVideos.collectLatest { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        loading = false
                        videosList = result.data ?: emptyList()
                    }

                    is NetworkResult.Error -> {
                        loading = false
                    }

                    is NetworkResult.Loading -> {
                        loading = false
                    }
                }
            }
        }

    }


    if (videosList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            MySpacerHeight(height = 24.dp)

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


                    SectionStickyHeader(
                        headerTitle = stringResource(R.string.video),
                        isHaveAnotherText = true,
                    )




                    MySpacerHeight(height = 8.dp)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(videosList) {
                            VideoItem(
                                poster = poster,
                                title = it.name ?: "",
                                type = it.type ?: "",
                                onClick = {
                                    navController.navigate(Screens.Video.route+"?key=${it.key}")

                                }
                            )
                        }


                    }

                    MySpacerHeight(height = 8.dp)

                }
            }
        }
    }
}


