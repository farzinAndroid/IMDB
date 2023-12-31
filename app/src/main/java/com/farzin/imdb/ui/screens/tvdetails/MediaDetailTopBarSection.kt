package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.red
import com.farzin.imdb.ui.theme.whiteBackground
import com.farzin.imdb.utils.Constants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaDetailTopBarSection(
    name: String,
    shouldHaveLikeButton: Boolean = false,
    onClick: () -> Unit = {},
    likeButtonOnClick: () -> Unit = {},
    isFavorite:Boolean = false
) {

    val rotate = if (Constants.USER_LANG == Constants.PERSIAN)
        180f else 0f

    Row(
        modifier = Modifier
            .shadow(2.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.whiteBackground),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.9f)
                .padding(start = 8.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "",
                modifier = Modifier
                    .size(26.dp)
                    .clickable { onClick() }
                    .rotate(rotate),
            )

            Text(
                text = name,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 20.dp,
                    ),
                fontWeight = FontWeight.SemiBold,
            )

        }

        if (shouldHaveLikeButton) {

            val icon = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder

            IconButton(
                onClick = {
                    likeButtonOnClick()
                },
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
                    .weight(0.1f),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.red
                )
            }


        }
    }
}