package com.farzin.imdb.ui.screens.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.mediaDetail.TVReviewModelResult
import com.farzin.imdb.ui.screens.tvdetails.MediaDetailTopBarSection
import com.farzin.imdb.viewmodel.MediaDetailViewModel

@Composable
fun CommentScreen(
    mediaId: Int,
    navController: NavController,
    mediaDetailViewModel: MediaDetailViewModel = hiltViewModel(),
) {


    LaunchedEffect(true) {
        mediaDetailViewModel.getReviewsForTV(mediaId, 1)
    }

    var loading by remember { mutableStateOf(false) }
    var reviewList by remember { mutableStateOf<List<TVReviewModelResult>>(emptyList()) }
    var totalResult by remember { mutableStateOf(0) }


    val result by mediaDetailViewModel.reviewsTV.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            reviewList = result.data?.results ?: emptyList()
            totalResult = result.data?.total_results ?: 0


        }

        is NetworkResult.Error -> {
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (!loading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            LazyColumn(){

                item {
                    MediaDetailTopBarSection(name = stringResource(R.string.user_review)) {
                        navController.popBackStack()
                    }
                }

                item {
                    CommentResultSection(totalResult)
                }

                items(reviewList){comment->
                    CommentItem(comment)
                }
            }

        }
    }
}