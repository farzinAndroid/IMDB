package com.farzin.imdb.ui.screens.images_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.farzin.imdb.models.tvDetail.Backdrop
import com.farzin.imdb.ui.screens.cast_detail.ImageScreenState
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.viewmodel.ImageScreenViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import me.saket.telephoto.zoomable.coil.ZoomableAsyncImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageFullScreen(
    imageScreenViewModel: ImageScreenViewModel = hiltViewModel(),
    path: String,
    imageList: List<Backdrop> = emptyList(),
    index:Int = 0
) {

    BackHandler(enabled = true) {
        imageScreenViewModel.imageScreenState = ImageScreenState.IMAGE_LIST
    }

    val pagerState = rememberPagerState()
    var backdropPath by remember { mutableStateOf("") }


//    Box {
//
//        HorizontalPager(
//            count = imageList.size,
//            state = pagerState,
//            modifier = Modifier
//                .fillMaxSize()
//        ) { index ->
//            backdropPath = imageList[index].file_path
//
//                ZoomableAsyncImage(
//                    modifier = Modifier.fillMaxSize(),
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(ImageHelper.appendImage(backdropPath))
//                        .listener(
//                            remember {
//                                object : ImageRequest.Listener {
//                                    override fun onSuccess(
//                                        request: ImageRequest,
//                                        result: SuccessResult,
//                                    ) {
//                                    }
//
//                                    override fun onError(
//                                        request: ImageRequest,
//                                        result: ErrorResult,
//                                    ) {
//                                    }
//                                }
//                            }
//                        )
//                        .crossfade(500)
//                        .memoryCachePolicy(CachePolicy.DISABLED)
//                        .build(),
//                    imageLoader = LocalContext.current.imageLoader, // Optional.
//                    contentDescription = "",
//                )
//
//        }
//
//    }


                    ZoomableAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ImageHelper.appendImage(path))
                        .listener(
                            remember {
                                object : ImageRequest.Listener {
                                    override fun onSuccess(
                                        request: ImageRequest,
                                        result: SuccessResult,
                                    ) {}

                                    override fun onError(
                                        request: ImageRequest,
                                        result: ErrorResult,
                                    ) {}
                                }
                            }
                        )
                        .crossfade(500)
                        .memoryCachePolicy(CachePolicy.DISABLED)
                        .build(),
                    imageLoader = LocalContext.current.imageLoader, // Optional.
                    contentDescription = "",
                )



}