package com.farzin.imdb.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.farzin.imdb.R
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.My3DotsLoading
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PopularTVSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {


   /* var popularTV by remember {
        mutableStateOf<List<TVModelResult>>(
            emptyList()
        )
    }*/

    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current


    /*var loading by remember { mutableStateOf(false) }*/

    val popularTV = homeViewModel.popularTV.collectAsLazyPagingItems()
    /*when (result) {
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
    }*/


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

                SectionStickyHeader(stringResource(R.string.popular_among_watchers))

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(
                        count = popularTV.itemCount,
                        contentType = popularTV.itemContentType { "PopularTV" }
                    ) {itemInt->
                        val popularTvList = popularTV[itemInt]

                        MovieItem(
                            posterPath = popularTvList?.poster_path ?: "",
                            voteAverage = popularTvList?.vote_average ?: 0.0,
                            name = popularTvList?.name ?: "",
                            releaseDate = popularTvList?.first_air_date ?: "",
                            onCardClicked = {
                                navController.navigate(
                                    Screens.TVDetails.route + "?id=${popularTvList?.id}"
                                )
                            },
                            onAddButtonClicked = {
                                homeViewModel.addToWatchList(
                                    AddToWatchListRequest(
                                        media_id = popularTvList?.id ?: 0,
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

                    popularTV.loadState.apply {
                        when{
                            refresh is LoadState.Loading->{
                                item {
                                    My3DotsLoading(modifier = Modifier
                                        .fillMaxSize())
                                }
                            }

                            append is LoadState.Loading->{
                                item {
                                        My3DotsLoading(modifier = Modifier
                                            .fillMaxHeight()
                                            .width(100.dp))

                                }
                            }
                        }
                    }

                }

                MySpacerHeight(height = 20.dp)
            }


        }

    }


}