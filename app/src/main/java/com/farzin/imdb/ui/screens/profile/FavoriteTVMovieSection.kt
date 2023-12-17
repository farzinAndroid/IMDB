package com.farzin.imdb.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.farzin.imdb.models.database.FavoriteDBModel
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.EmptySection
import com.farzin.imdb.ui.screens.home.MovieItem
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.DigitHelper
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteTVMovieSection(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {


    var favMovieTVList by remember { mutableStateOf<List<FavoriteDBModel>>(emptyList()) }
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current

    LaunchedEffect(true) {
        profileViewModel.allFavorites.collectLatest { result ->
            favMovieTVList = result
        }
    }


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
                    headerTitle = "${stringResource(R.string.fav_media)} ${
                        DigitHelper.digitByLang(
                            favMovieTVList.size.toString()
                        )
                    }",
                )

                MySpacerHeight(height = 8.dp)

                if (favMovieTVList.isEmpty() && Constants.SESSION_ID.isNotEmpty()) {

                    EmptySection(
                        onClick = {
                            navController.navigate(Screens.Search.route)
                        },
                        title = stringResource(R.string.fav_media_empty),
                        subtitle = stringResource(R.string.add_your_fav_celeb),
                        isHaveButton = false
                    )

                } else {

                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(
                            favMovieTVList,
                            key = { it.id }
                        ) { item ->
                            MovieItem(
                                posterPath = item.image,
                                voteAverage = item.rating,
                                name = item.name,
                                releaseDate = item.year,
                                modifier = Modifier.animateItemPlacement(),
                                onCardClicked = {
                                    if (item.isMovie) {
                                        navController.navigate(Screens.MovieDetails.route + "?id=${item.id}")
                                    } else {
                                        navController.navigate(Screens.TVDetails.route + "?id=${item.id}")
                                    }
                                },
                                onAddButtonClicked = {
                                    if (item.isMovie){
                                        homeViewModel.addToWatchList(
                                            AddToWatchListRequest(
                                                media_id = item.id,
                                                media_type = "movie",
                                                watchlist = true
                                            )
                                        )
                                        scope.launch {
                                            delay(200)
                                            homeViewModel.getWatchListMovie()
                                            Toast.makeText(ctx,ctx.getString(R.string.added_to_watchList),Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
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
                                            Toast.makeText(ctx,ctx.getString(R.string.added_to_watchList),Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                }
                            )
                        }
                    }

                }

                MySpacerHeight(height = 10.dp)

            }


        }
    }

}