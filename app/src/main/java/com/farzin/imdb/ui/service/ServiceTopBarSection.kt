package com.farzin.imdb.ui.service

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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.ui.theme.whiteBackground
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun ServiceTopBarSection(navController: NavController) {


    val rotate = if (Constants.USER_LANG == Constants.PERSIAN)
        180f else 0f

    Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(2.dp)
                .background(MaterialTheme.colorScheme.whiteBackground),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            MySpacerWidth(width = 16.dp)

            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "",
                modifier = Modifier
                    .size(26.dp)
                    .clickable { navController.popBackStack() }
                    .rotate(rotate)
            )

            Text(
                text = stringResource(R.string.add_service),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.normalText,
                modifier = Modifier
                    .padding(start = 16.dp)
            )

        }




}