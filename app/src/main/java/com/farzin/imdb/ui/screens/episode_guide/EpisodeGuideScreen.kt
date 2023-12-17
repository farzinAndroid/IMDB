package com.farzin.imdb.ui.screens.episode_guide

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.episode_guide.Episode
import com.farzin.imdb.models.tvDetail.Season
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailTopBarSection
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.viewmodel.EpisodeGuideViewModel
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EpisodeGuideScreen(
    mediaId: Int,
    navController: NavController,
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    episodeGuideViewModel: EpisodeGuideViewModel = hiltViewModel(),
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var loading by remember { mutableStateOf(false) }
    var seasonList by remember { mutableStateOf<List<Season>>(emptyList()) }
    var seasonNumber by remember { mutableIntStateOf(0) }

    LaunchedEffect(mediaId) {
        tvDetailViewModel.getTVDetails(mediaId)

        tvDetailViewModel.tvDetails.collectLatest { result ->
            when (result) {
                is NetworkResult.Success -> {
                    loading = false
                    seasonList = result.data?.seasons ?: emptyList()
                    seasonNumber = result.data?.number_of_seasons ?: 0
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

    if (seasonList.isNotEmpty()) {

        Column {

            MediaDetailTopBarSection(
                name = stringResource(R.string.episodes),
                onClick = {
                    navController.popBackStack()
                }
            )

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                indicator = { line ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(line[selectedTabIndex])
                            .height(3.dp)
                            .background(MaterialTheme.colorScheme.imdbYellow)

                    )
                },
                modifier = Modifier.fillMaxWidth(),
                tabs = {
                    seasonList.forEachIndexed { index, season ->

                        Tab(
                            selected = (selectedTabIndex == index),
                            onClick = {
                                selectedTabIndex = index
                            },
                            selectedContentColor = MaterialTheme.colorScheme.imdbYellow,
                            unselectedContentColor = MaterialTheme.colorScheme.strongGray,
                            text = {
                                Row {
                                    if (season.season_number == 0) {
                                        Text(
                                            text = stringResource(R.string.special),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.darkText,
                                        )
                                    } else {
                                        Text(
                                            text = season.season_number.toString(),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.darkText,
                                        )
                                    }

                                }
                            },
                        )

                    }
                },
                edgePadding = 0.dp,
                divider = {
                    Divider(color = Color.Transparent)
                }
            )

            val selectedSeason = seasonList.getOrNull(selectedTabIndex)
            if (selectedSeason != null) {
                val seasonNumber = selectedSeason.season_number

                var episodeList by remember { mutableStateOf<List<Episode>>(emptyList()) }
                var name by remember { mutableStateOf("") }
                var airDate by remember { mutableStateOf("") }
                var vote by remember { mutableDoubleStateOf(0.0) }
                var posterPath by remember { mutableStateOf("") }
                var overView by remember { mutableStateOf("") }

                LaunchedEffect(selectedTabIndex) {
                    episodeGuideViewModel.getSeasonDetails(mediaId, seasonNumber)
                    episodeGuideViewModel.seasonDetails.collectLatest { result ->
                        episodeList = result.data?.episodes ?: emptyList()

                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(episodeList) { item ->
                        name = item.name ?: ""
                        airDate = item.air_date ?: ""
                        vote = item.vote_average ?: 0.0
                        posterPath = item.still_path ?: ""
                        overView = item.overview ?: ""

                        EpisodeItem(
                            name = name,
                            airDate = airDate,
                            vote = vote,
                            posterPath = posterPath,
                            overView = overView
                        )
                    }
                }
            }

        }


    }


}