package com.farzin.imdb.ui.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.ui.theme.whiteBackground
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun ServiceItem(
    item: ServiceModel,
    onClick: () -> Unit,
    isSelected: Boolean,
) {


    val iconTint = if (isSelected) MaterialTheme.colorScheme.imdbYellow
    else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.whiteBackground),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            MySpacerWidth(width = 8.dp)

            item.logo?.let {
                Image(
                    painter = it,
                    contentDescription = "",
                    modifier = Modifier
                        .size(26.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            MySpacerWidth(width = 8.dp)

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.normalText,
                modifier = Modifier
                    .padding(start = 4.dp),
                fontWeight = FontWeight.SemiBold
            )
        }



        Icon(
            painter = painterResource(R.drawable.check),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 8.dp)
                .size(14.dp),
            tint = iconTint
        )


    }


    MyDividerHorizontal()

}