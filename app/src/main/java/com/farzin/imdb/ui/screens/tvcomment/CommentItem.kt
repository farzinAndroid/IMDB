package com.farzin.imdb.ui.screens.tvcomment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.imdb.R
import com.farzin.imdb.models.tvDetail.TVReviewModelResult
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.font_standard
import com.farzin.imdb.ui.theme.starBlue
import com.farzin.imdb.utils.DateHelper
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight

@Composable
fun CommentItem(comment: TVReviewModelResult) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(bottom = 8.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            if (comment.author_details.rating != null){
                Icon(
                    painter = painterResource(R.drawable.star_fill),
                    contentDescription = "",
                    modifier = Modifier
                        .size(18.dp),
                    tint = MaterialTheme.colorScheme.starBlue
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append(comment.author_details.rating.toString())
                        }


                        withStyle(
                            style = SpanStyle(
                                fontFamily = font_standard,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.darkText
                            )
                        ) {
                            append("/10")
                        }

                    },
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }


            Text(
                text = comment.author_details.username,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Start
            )

        }

        Text(
            text = "${DateHelper.formatDate(comment.created_at)}",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Text(
            text = comment.content,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.darkText,
            textAlign = TextAlign.Start
        )

        MySpacerHeight(height = 8.dp)

        MyDividerHorizontal()

    }

}