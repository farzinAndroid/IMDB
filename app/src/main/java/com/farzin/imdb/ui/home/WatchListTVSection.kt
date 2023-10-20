package com.farzin.imdb.ui.home

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
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.WatchListTV
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun WatchListTVSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()

    var watchListTVList by remember {
        mutableStateOf<WatchListTV>(
            WatchListTV()
        )
    }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.watchListTV.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            watchListTVList = result.data ?: WatchListTV()
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

        val height = if (watchListTVList.results.isEmpty()) 350.dp else 480.dp

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
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
                    headerTitle = stringResource(R.string.your_watch_list_tv),
                    isHaveAnotherText = true,
                    headerSubtitle = stringResource(R.string.refresh),
                    headerOnClick = {
                        scope.launch {
                            homeViewModel.getWatchListTV()
                        }
                    }
                )

                MySpacerHeight(height = 8.dp)

                if (watchListTVList.results.isEmpty()) {
                    EmptySection(
                        onClick = {},
                        title = stringResource(R.string.empty_watchlist_title),
                        subtitle = stringResource(R.string.empty_watchlist_subtitle),
                        buttonText = stringResource(R.string.empty_watchlist_button_text)
                    )
                } else {

                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(watchListTVList.results) {
                            MovieItem(
                                item = it,
                                onClick = {
                                    homeViewModel.addToWatchList(
                                        AddToWatchListRequest(
                                            media_id = it.id,
                                            media_type = "tv",
                                            watchlist = false
                                        )
                                    )
                                    scope.launch {
                                        homeViewModel.getWatchListTV()
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