package com.farzin.imdb.ui.screens.all_cast

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.R
import com.farzin.imdb.models.database.PersonDBModel
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastItem(
    name: String,
    profilePath: String,
    character: String,
    numberOfEpisode: Int = 0,
    onCardClicked: () -> Unit,
    id: Int,
    job: String,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {


    val scope = rememberCoroutineScope()

    var isSaved by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        scope.launch(Dispatchers.IO) {
            isSaved = profileViewModel.getId(id) == id
        }
    }

    val heart = if (isSaved) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onCardClicked() }) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    ImageHelper.appendImage(profilePath),
                    imageLoader = ImageLoader.Builder(LocalContext.current)
                        .crossfade(true)
                        .crossfade(500)
                        .build(),
                    contentScale = ContentScale.FillBounds
                ),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 8.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.6f)
            ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText
                )

                MySpacerHeight(height = 6.dp)

                Text(
                    text = character,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .basicMarquee(
                            iterations = Int.MAX_VALUE,
                            animationMode = MarqueeAnimationMode.Immediately,
                            spacing = MarqueeSpacing(8.dp),
                            velocity = 20.dp,
                        ),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.strongGray
                )

                if (numberOfEpisode != 0){
                    MySpacerHeight(height = 6.dp)

                    Text(
                        text = "($numberOfEpisode ${stringResource(R.string.episodes)})",
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.strongGray
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f)
            ) {

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(0.5f))
                        .clickable {
                            isSaved = !isSaved
                            if (isSaved) {
                                profileViewModel.addPerson(
                                    PersonDBModel(
                                        id = id,
                                        job = job,
                                        name = name,
                                        image = profilePath
                                    )
                                )
                            } else {
                                profileViewModel.removePerson(
                                    PersonDBModel(
                                        id = id,
                                        job = job,
                                        name = name,
                                        image = profilePath
                                    )
                                )
                            }
                        }
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center),
                        imageVector = heart,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.imdbYellow
                    )
                }

            }

        }

        MyDividerHorizontal()

    }


}