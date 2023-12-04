package com.farzin.imdb.ui.screens.images_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.tvDetail.Backdrop
import com.farzin.imdb.ui.screens.cast_detail.ImageScreenState
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailTopBarSection
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.viewmodel.ImageScreenViewModel
import com.farzin.imdb.viewmodel.MovieDetailViewModel
import com.farzin.imdb.viewmodel.TVDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ImagesListScreen(
    mediaType: String,
    mediaId: Int,
    navController: NavController,
    tvDetailViewModel: TVDetailViewModel = hiltViewModel(),
    imageScreenViewModel: ImageScreenViewModel = hiltViewModel(),
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {

    var imageList by remember { mutableStateOf<List<Backdrop>>(emptyList()) }
    var path by remember { mutableStateOf("") }
    var index by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    if (mediaType == "tv") {
        LaunchedEffect(mediaId) {
            tvDetailViewModel.getImagesForTV(mediaId)
            tvDetailViewModel.imagesForTV.collectLatest { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        imageList = result.data?.backdrops ?: emptyList()
                        loading = false
                    }

                    is NetworkResult.Error -> {
                        loading = false
                    }

                    is NetworkResult.Loading -> {
                        loading = true
                    }
                }
            }
        }
    } else {
        LaunchedEffect(mediaId) {
            movieDetailViewModel.getImagesForMovie(mediaId)
            movieDetailViewModel.movieImages.collectLatest { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        imageList = result.data?.backdrops ?: emptyList()
                        loading = false
                    }

                    is NetworkResult.Error -> {
                        loading = false
                    }

                    is NetworkResult.Loading -> {
                        loading = true
                    }
                }
            }
        }
    }



    when(imageScreenViewModel.imageScreenState){
        ImageScreenState.IMAGE_LIST->{
            if (imageList.isNotEmpty()) {

                Column {

                    MediaDetailTopBarSection(
                        name = stringResource(R.string.images),
                        shouldHaveThreeDotMenu = false,
                        onClick = {navController.popBackStack()}
                    )

                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        state = rememberLazyGridState(),
                        columns = GridCells.Fixed(2)
                    ) {

                        items(imageList) { item ->
                            ImageListItem(path = item.file_path) {
                                path = item.file_path
                                imageScreenViewModel.imageScreenState = ImageScreenState.IMAGE_FULLSCREEN
                            }
                        }

                    }

                }


            }
        }
        ImageScreenState.IMAGE_FULLSCREEN->{
                ImageFullScreen(path = path)
        }
    }




}

@Composable
private fun ImageListItem(
    path: String,
    onClick: () -> Unit,
) {

    val ctx = LocalContext.current

    Image(
        painter = rememberAsyncImagePainter(
            ImageHelper.appendImage(path),
            imageLoader = ImageLoader.Builder(ctx)
                .crossfade(true)
                .crossfade(500)
                .build()
        ),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(4.dp)
            .clickable { onClick() },
        contentScale = ContentScale.FillBounds
    )

}