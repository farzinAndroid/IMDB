package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.farzin.imdb.models.database.PersonDBModel
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.utils.ImageHelper
import com.farzin.imdb.utils.MySpacerWidth
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CastCardItem(
    name: String,
    profilePath: String,
    character: String,
    onCardClicked: () -> Unit,
    id: Int,
    job: String,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

    var isSaved by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            isSaved = profileViewModel.getPersonId(id) == id
        }
    }

    val heart = if (isSaved) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder


    MySpacerWidth(width = 10.dp)

    Card(
        modifier = modifier
            .width(150.dp)
            .height(350.dp)
            .clickable { onCardClicked() },
        shape = Shapes().small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.sectionContainerBackground),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageHelper.appendImage(profilePath),
                        contentScale = ContentScale.FillBounds
                    ),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
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



            Text(
                text = name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 4.dp)
                    .padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = character,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.strongGray,
                modifier = Modifier
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 20.dp,
                    )
                    .weight(0.1f)
                    .padding(start = 4.dp)
                    .padding(top = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}