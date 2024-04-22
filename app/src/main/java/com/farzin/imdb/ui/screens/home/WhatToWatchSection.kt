package com.farzin.imdb.ui.screens.home

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.TVModelResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WhatToWatchSection(
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        dataStoreViewModel.getServiceId()?.let {
            homeViewModel.getTvBasedOnNetwork(it)
        }
    }

    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current


    var tvBasedOnNetworkList by remember {
        mutableStateOf<List<TVModelResult>>(
            emptyList()
        )
    }

    var loading by remember { mutableStateOf(false) }


    val result by homeViewModel.tVBasedOnNetwork.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            tvBasedOnNetworkList = result.data?.results ?: emptyList()
        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 20.dp)

        SectionTitleMaker(title = stringResource(R.string.what_to_watch))

        MySpacerHeight(height = 16.dp)



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


            if (
                dataStoreViewModel.getServiceId() == null ||
                dataStoreViewModel.getServiceId() == 0
            ) {
                EmptySection(
                    onClick = { navController.navigate(Screens.Service.route) },
                    title = stringResource(R.string.add_service_preffered),
                    subtitle = stringResource(R.string.add_service_preffered_desc),
                    buttonText = stringResource(R.string.add_service)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {

                    SectionStickyHeader(
                        stringResource(R.string.trending_on_service),
                        isHaveAnotherText = true,
                        headerSubtitle = stringResource(R.string.edit_services),
                        headerOnClick = {
                            navController.navigate(Screens.Service.route)
                        }
                    )

                    MySpacerHeight(height = 8.dp)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(
                            tvBasedOnNetworkList,
                            key = { it.id }
                        ) { item ->

                            MovieItem(
                                posterPath = item.poster_path ?: "",
                                voteAverage = item.vote_average,
                                name = item.name,
                                releaseDate = item.first_air_date ?: "",
                                onCardClicked = {
                                    navController.navigate(
                                        Screens.TVDetails.route + "?id=${item.id}"
                                    )
                                },
                                onAddButtonClicked = {
                                    homeViewModel.addToWatchList(
                                        AddToWatchListRequest(
                                            media_id = item.id,
                                            media_type = "tv",
                                            watchlist = true
                                        )
                                    )
                                    scope.launch {
                                        delay(200)
                                        homeViewModel.getWatchListTV()
                                        Toast.makeText(ctx,ctx.getString(R.string.added_to_watchList),
                                            Toast.LENGTH_SHORT).show()
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

}






