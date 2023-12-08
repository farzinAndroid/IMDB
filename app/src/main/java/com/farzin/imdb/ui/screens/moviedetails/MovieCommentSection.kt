package com.farzin.imdb.ui.screens.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.tvDetail.TVReviewModelResult
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.screens.tvdetails.CommentCard
import com.farzin.imdb.ui.screens.tvdetails.MediaNoCommentCard
import com.farzin.imdb.ui.theme.Cyan
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.font_standard
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.ui.theme.starBlue
import com.farzin.imdb.utils.DigitHelper
import com.farzin.imdb.utils.My3DotsLoading
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.utils.MySpacerWidth
import com.farzin.imdb.viewmodel.MovieDetailViewModel

@Composable
fun MovieCommentSection(
    mediaId: Int,
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    rating:String,
    userRating:Int
) {


    LaunchedEffect(true) {
        movieDetailViewModel.getMovieReviews(mediaId)
    }

    var loading by remember { mutableStateOf(false) }
    var reviewList by remember { mutableStateOf<List<TVReviewModelResult>>(emptyList()) }


    val result by movieDetailViewModel.movieReviews.collectAsState()
    when (result) {
        is NetworkResult.Success -> {
            loading = false
            reviewList = result.data?.results ?: emptyList()

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


                SectionStickyHeader(stringResource(R.string.user_review))

                if (loading){
                    My3DotsLoading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                            .padding(bottom = 12.dp)
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(
                                painter = painterResource(R.drawable.star_unfill),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(26.dp),
                                tint = MaterialTheme.colorScheme.imdbYellow
                            )

                            MySpacerWidth(width = 4.dp)

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = font_standard,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 30.sp,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    ) {
                                        append(rating)
                                    }


                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = font_standard,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 20.sp,
                                            color = MaterialTheme.colorScheme.darkText
                                        )
                                    ) {
                                        append("/${DigitHelper.digitByLang("10")}")
                                    }

                                }
                            )


                            MySpacerWidth(width = 24.dp)


                            if (userRating != 0) {
                                Icon(
                                    painter = painterResource(R.drawable.star_unfill),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(26.dp),
                                    tint = MaterialTheme.colorScheme.starBlue
                                )

                                MySpacerWidth(width = 4.dp)

                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = font_standard,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 30.sp,
                                                color = MaterialTheme.colorScheme.darkText
                                            )
                                        ) {
                                            append(DigitHelper.digitByLang(userRating.toString()))
                                        }


                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = font_standard,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.darkText
                                            )
                                        ) {
                                            append("/${DigitHelper.digitByLang("10")}")
                                        }

                                    }
                                )
                            }
                        }
                    }

                    MySpacerHeight(height = 8.dp)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        if (reviewList.isEmpty()) {
                            item {
                                MediaNoCommentCard()
                            }
                        } else {
                            items(reviewList) {
                                CommentCard(
                                    item = it,
                                    onClick = {
                                    }
                                )
                            }
                        }
                    }

                    MySpacerHeight(height = 20.dp)


                    if (!reviewList.isNullOrEmpty()){
                        TextButton(
                            onClick = { navController.navigate(Screens.MovieComment.route+"?id=${mediaId}") }
                        ) {
                            Text(
                                text = stringResource(R.string.view_all_comments),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.Cyan,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }




            }
        }
    }

}