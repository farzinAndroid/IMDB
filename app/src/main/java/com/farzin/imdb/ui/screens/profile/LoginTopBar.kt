package com.farzin.imdb.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.imdb.R
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.selectedColor
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun LoginTopBar(navController: NavController) {

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(2.dp)
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){

            MySpacerWidth(width = 8.dp)

            Icon(
                painter = painterResource(R.drawable.profile),
                contentDescription = "",
                modifier = Modifier
                    .size(26.dp),
                tint = MaterialTheme.colorScheme.imdbYellow
            )


            MySpacerWidth(width = 24.dp)

            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                fontWeight = FontWeight.SemiBold
            )

        }



        Row(horizontalArrangement = Arrangement.End) {
            IconButton(
                onClick = { navController.navigate(Screens.Settings.route) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = "",
                    modifier = Modifier
                        .size(26.dp),
                    tint = MaterialTheme.colorScheme.selectedColor,
                )
            }



            MySpacerWidth(width = 8.dp)
        }

    }


}

