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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.HomeViewModel

@Composable
fun WhatToWatchSection(
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(true){
        dataStoreViewModel.getServiceId()?.let {
            homeViewModel.getTvBasedOnNetwork(it)
        }
    }


    var tvBasedOnNetworkList by remember {
        mutableStateOf<TVBasedOnNetwork>(
            TVBasedOnNetwork()
        )
    }

    var loading by remember { mutableStateOf(false) }


    val result by homeViewModel.tVBasedOnNetwork.collectAsState()
    when(result){
        is NetworkResult.Success->{
            loading = false
            tvBasedOnNetworkList = result.data ?: TVBasedOnNetwork()
        }
        is NetworkResult.Error->{
            loading = false
        }
        is NetworkResult.Loading->{
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
                .height(480.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
            shape = MaterialTheme.shapes.extraSmall
        ) {


            if (dataStoreViewModel.getServiceId() == null) {
                EmptyWatchList { navController.navigate(Screens.Service.route) }
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
                            ){
                                items(tvBasedOnNetworkList.results){item->
                                    MovieItem(item = item)
                                }
                            }
                        }

            }


        }

    }

}






