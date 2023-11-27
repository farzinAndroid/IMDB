package com.farzin.imdb.ui.screens.tvdetails

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.tvDetail.Backdrop
import com.farzin.imdb.models.tvDetail.Logo
import com.farzin.imdb.models.tvDetail.Poster
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.TVDetailViewModel

@Composable
fun TVImageSection(
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    mediaId: Int,
) {

    LaunchedEffect(true) {
        tvDetailViewModel.getImagesForTV(mediaId)
    }

    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }
    var imageBackdropList by remember { mutableStateOf<List<Backdrop>>(emptyList()) }
    var imageLogoList by remember { mutableStateOf<List<Logo>>(emptyList()) }
    var imagePosterList by remember { mutableStateOf<List<Poster>>(emptyList()) }
    var imageNumber by remember { mutableStateOf(0) }


    val result by tvDetailViewModel.imagesForTV.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            imageBackdropList = result.data?.backdrops?.take(20) ?: emptyList()
            imageLogoList = result.data?.logos ?: emptyList()
            imagePosterList = result.data?.posters ?: emptyList()
            imageNumber = result.data?.posters?.size ?: 0
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

        MySpacerHeight(height = 24.dp)

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
                    headerTitle = stringResource(R.string.photos),
                    isHaveAnotherText = true,
                    headerSubtitle = "${stringResource(R.string.see_all)} $imageNumber ${stringResource(R.string.photos)}"
                )




                MySpacerHeight(height = 8.dp)

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(imageBackdropList) {
                        ImageItem(path = it.file_path)
                    }

                    item{ ShowMoreItem(200.dp,150.dp){} }

                }

                MySpacerHeight(height = 8.dp)

            }
        }
    }

}