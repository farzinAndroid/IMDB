package com.farzin.imdb.ui.screens.tvdetails

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
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.mediaDetail.Cast
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.MediaDetailViewModel

@Composable
fun MediaCastSection(
    mediaDetailViewModel: MediaDetailViewModel = hiltViewModel(),
    mediaId: Int,
) {

    LaunchedEffect(true) {
        mediaDetailViewModel.getTVCastAndCrew(mediaId)
    }

    var loading by remember { mutableStateOf(false) }
    var castList by remember { mutableStateOf<List<Cast>>(emptyList()) }


    val result by mediaDetailViewModel.castAndCrew.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            castList = result.data?.cast?.take(20) ?: emptyList()
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

                SectionStickyHeader(stringResource(R.string.cast))

                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(castList){cast->
                        CastCardItem(cast = cast)
                    }

                    item {
                        ShowMoreItem()
                    }

                }
            }


        }

    }



}