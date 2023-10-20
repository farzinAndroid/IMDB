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
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.PopularTVModelResult
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun PopularTVSection(homeViewModel: HomeViewModel = hiltViewModel()) {


    var popularTV by remember {
        mutableStateOf<List<PopularTVModelResult>>(
            emptyList()
        )
    }

    val scope = rememberCoroutineScope()


    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.popularTV.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            popularTV = result.data?.results ?: emptyList()
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

        SectionTitleMaker(title = stringResource(R.string.popular_section))

        MySpacerHeight(height = 16.dp)

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

                SectionStickyHeader(stringResource(R.string.popular_among_watchers))

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(popularTV) {
                        MovieItem(
                            item = it,
                            onClick = {
                                homeViewModel.addToWatchList(
                                    AddToWatchListRequest(
                                        media_id = it.id,
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

    }


}