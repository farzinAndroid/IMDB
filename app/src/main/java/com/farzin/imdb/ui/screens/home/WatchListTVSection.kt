package com.farzin.imdb.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.farzin.imdb.R
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.utils.SourceHelper.emptyPagingData
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchListTVSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navController: NavController,
) {

    val scope = rememberCoroutineScope()

    val watchListTVList = homeViewModel.watchListTV.collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        MySpacerHeight(height = 20.dp)



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




                    if (watchListTVList == emptyPagingData.collectAsLazyPagingItems() && Constants.SESSION_ID.isNotEmpty()) {

                        EmptySection(
                            onClick = {
                                navController.navigate(Screens.Search.route)
                            },
                            title = stringResource(R.string.empty_watchlist_title),
                            subtitle = stringResource(R.string.empty_watchlist_subtitle),
                            buttonText = stringResource(R.string.empty_watchlist_button_text)
                        )

                    } else if (Constants.SESSION_ID == "" && watchListTVList== emptyPagingData.collectAsLazyPagingItems()) {
                        EmptySection(
                            onClick = { navController.navigate(Screens.Profile.route) },
                            title = stringResource(R.string.please_login_to_see_watchlist),
                            subtitle = stringResource(R.string.empty_watchlist_subtitle),
                            isHaveButton = true,
                            buttonText = stringResource(R.string.login)
                        )
                    } else {

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(
                                count = watchListTVList.itemCount,
                                key = watchListTVList.itemKey{it.id},
                                contentType = watchListTVList.itemContentType { "watchListTVList" }
                            ) {

                                val list = watchListTVList[it]
                                MovieItem(
                                    posterPath = list?.poster_path ?: "",
                                    voteAverage = list?.vote_average ?: 0.0,
                                    name = list?.name ?: "",
                                    releaseDate = list?.first_air_date ?: "",
                                    onCardClicked = {
                                        navController.navigate(
                                            Screens.TVDetails.route + "?id=${list?.id}"
                                        )
                                    },
                                    onAddButtonClicked = {
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = list?.id ?: 0,
                                                media_type = "tv",
                                                watchlist = false
                                            )
                                        )
                                        scope.launch {
                                            delay(200)
                                            homeViewModel.getWatchListTV()
                                        }
                                    },
                                    isFromWatchlist = true,
                                    modifier = Modifier.animateItemPlacement()
                                )
                            }
                        }

                    }
                }



                MySpacerHeight(height = 20.dp)

            }


        }

    }