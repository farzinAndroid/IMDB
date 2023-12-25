package com.farzin.imdb.ui.screens.tvdetails

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.database.FavoriteDBModel
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.tvDetail.Genre
import com.farzin.imdb.models.tvDetail.Network
import com.farzin.imdb.models.tvDetail.ProductionCountry
import com.farzin.imdb.models.tvDetail.SpokenLanguage
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.cast_detail.ImageScreenState
import com.farzin.imdb.ui.screens.images_screen.ImageFullScreen
import com.farzin.imdb.ui.theme.appBackGround
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.MyLoadingFullScreen
import com.farzin.imdb.viewmodel.HomeViewModel
import com.farzin.imdb.viewmodel.ImageScreenViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun TVDetailsScreen(
    tvId: Int,
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    imageScreenViewModel: ImageScreenViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var isInFavorite by remember { mutableStateOf(false) }
    LaunchedEffect(tvId) {
        scope.launch(Dispatchers.IO) {
            isInFavorite = profileViewModel.getFavoriteId(tvId) == tvId
        }
    }

    var isLoggedIn by remember { mutableStateOf(false) }
    isLoggedIn = Constants.SESSION_ID.isNotEmpty()


    var loading by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var numberOfEpisode by remember { mutableIntStateOf(0) }
    var runTime by remember { mutableStateOf<List<Int>>(emptyList()) }
    var startYear by remember { mutableStateOf("") }
    var endYear by remember { mutableStateOf("") }
    var picturePath by remember { mutableStateOf("") }
    var posterPath by remember { mutableStateOf("") }
    var overView by remember { mutableStateOf("") }
    var genres by remember { mutableStateOf<List<Genre>>(emptyList()) }
    var rating by remember { mutableDoubleStateOf(0.0) }
    var voteCount by remember { mutableIntStateOf(0) }
    var userRating by remember { mutableIntStateOf(0) }
    var spokenLangList by remember { mutableStateOf<List<SpokenLanguage>>(emptyList()) }
    var productionCountry by remember { mutableStateOf<List<ProductionCountry>>(emptyList()) }
    var networks by remember { mutableStateOf<List<Network>>(emptyList()) }
    var originCountry by remember { mutableStateOf<List<String>>(emptyList()) }

    var imageFullScreenPath by remember { mutableStateOf("") }


    //get media details
    LaunchedEffect(tvId) {
        tvDetailViewModel.getTVDetails(tvId)

        tvDetailViewModel.tvDetails.collectLatest { result ->
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
                    spokenLangList = result.data?.spoken_languages ?: emptyList()
                    productionCountry = result.data?.production_countries ?: emptyList()
                    networks = result.data?.networks ?: emptyList()
                    originCountry = result.data?.origin_country ?: emptyList()
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

    // get if the TV is in watchlist
    LaunchedEffect(tvId) {
        homeViewModel.getWatchListTV()

        homeViewModel.watchListTV.collectLatest { result ->
            when (result) {
                is NetworkResult.Success -> {
                    isInWatchList = result.data?.results?.any {
                        tvId == it.id
                    } ?: false
                }

                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
            }
        }

    }


    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )


    if (loading) {
        MyLoadingFullScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp)
        )
    } else {
        when (imageScreenViewModel.imageScreenState) {
            ImageScreenState.IMAGE_LIST -> {
                ModalBottomSheetLayout(
                    sheetContent = {
                        TVRatingBottomSheet(name, tvId)
                    },
                    sheetState = sheetState

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.appBackGround)
                        ) {


                            stickyHeader {
                                MediaDetailTopBarSection(
                                    name = name,
                                    onClick = {
                                        navController.popBackStack()
                                    },
                                    shouldHaveLikeButton = true,
                                    likeButtonOnClick = {


                                        if (isLoggedIn){
                                            if (isInFavorite){
                                                profileViewModel.removeFavorite(
                                                    FavoriteDBModel(
                                                        id = tvId,
                                                        image = posterPath,
                                                        name = name,
                                                        year = startYear,
                                                        rating = rating,
                                                        isMovie = false
                                                    )
                                                )

                                                isInFavorite = false
                                            }else{
                                                profileViewModel.addFavorite(
                                                    FavoriteDBModel(
                                                        id = tvId,
                                                        image = posterPath,
                                                        name = name,
                                                        year = startYear,
                                                        rating = rating,
                                                        isMovie = false
                                                    )
                                                )
                                                isInFavorite = true
                                            }
                                        }else{
                                            Toast.makeText(context,context.getString(R.string.please_login),Toast.LENGTH_SHORT)
                                                .show()
                                        }


                                    },
                                    isFavorite = isInFavorite
                                )
                            }


                            item {
                                MediaDetailTitleSection(
                                    name = name,
                                    date = date,
                                    status = status,
                                    runTimeList = runTime,
                                    numberOfEpisode = numberOfEpisode,
                                    isMovie = false,
                                    onEpisodeGuideClicked = {
                                        navController.navigate(Screens.EpisodeGuide.route + "?id=$tvId")
                                    }
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
                                    mediaType = "tv"
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
                                TVRatingSection(
                                    rating = String.format("%.1f", rating),
                                    voteCount = voteCount,
                                    mediaId = tvId,
                                    onClick = {
                                        scope.launch {
                                            if (sheetState.isVisible)
                                                sheetState.hide() else sheetState.show()
                                        }
                                    },
                                    userRatingCallBack = {
                                        userRating = it
                                    }
                                )
                            }

                            item { TVCastSection(mediaId = tvId, navController = navController) }
                            item {
                                TVRecommendedSection(
                                    mediaId = tvId,
                                    navController = navController
                                )
                            }
                            item {
                                TVImageSection(
                                    mediaId = tvId,
                                    navController = navController,
                                    onImageClickCallBack = { path ->
                                        imageFullScreenPath = path
                                    },
                                    onImageClick = {
                                        imageScreenViewModel.imageScreenState =
                                            ImageScreenState.IMAGE_FULLSCREEN
                                    }
                                )
                            }
                            item {
                                MediaVideoSection(
                                    mediaId = tvId,
                                    mediaType = "tv",
                                    poster = posterPath,
                                    navController = navController
                                )
                            }
                            item {
                                TVCommentSection(
                                    mediaId = tvId,
                                    rating = String.format("%.1f", rating),
                                    userRating = userRating,
                                    navController = navController
                                )
                            }
                            item {
                                MediaDetailSection(
                                    spokenLangList = spokenLangList,
                                    productionCountry = productionCountry,
                                    networks = networks,
                                    originCountry = originCountry,
                                    releaseDate = startYear
                                )
                            }
                        }
                    }


                }
            }

            ImageScreenState.IMAGE_FULLSCREEN -> {
                ImageFullScreen(path = imageFullScreenPath)
            }
        }
    }

}
