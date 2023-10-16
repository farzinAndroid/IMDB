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
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.HomeViewModel

@Composable
fun TrendingMoviesForWeekSection(homeViewModel: HomeViewModel = hiltViewModel()) {


    var trendingMoviesList by remember {
        mutableStateOf<TrendingMoviesForWeek>(
            TrendingMoviesForWeek()
        )
    }

    var loading by remember { mutableStateOf(false) }

    val result by homeViewModel.trendingMoviesForWeek.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            trendingMoviesList = result.data ?: TrendingMoviesForWeek()
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

        SectionTitleMaker(title = stringResource(R.string.movies))

        MySpacerHeight(height = 16.dp)



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.sectionContainerBackground
            ),
            shape = MaterialTheme.shapes.extraSmall
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                SectionStickyHeader(stringResource(R.string.movies_of_the_week))

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(trendingMoviesList.results){
                        MovieItem(it)
                    }
                }
            }



        }



    }

}