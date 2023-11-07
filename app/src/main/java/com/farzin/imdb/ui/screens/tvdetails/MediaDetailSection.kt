package com.farzin.imdb.ui.screens.tvdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.R
import com.farzin.imdb.models.mediaDetail.Network
import com.farzin.imdb.models.mediaDetail.ProductionCountry
import com.farzin.imdb.models.mediaDetail.SpokenLanguage
import com.farzin.imdb.ui.screens.home.SectionStickyHeader
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.sectionContainerBackground
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.utils.MySpacerHeight

@Composable
fun MediaDetailSection(
    spokenLangList: List<SpokenLanguage>,
    productionCountry: List<ProductionCountry>,
    networks: List<Network>,
    originCountry: List<String>,
    releaseDate: String,
) {

    var spokenLang = ""
    spokenLangList.forEach {
        spokenLang += "${it.english_name}, "
    }

    var productionCountryText = ""
    productionCountry.forEach {
        productionCountryText += "${it.name}, "
    }

    var originCountryText = ""
    originCountry.forEach {
        originCountryText += "${it}, "
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


                SectionStickyHeader(stringResource(R.string.details))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = stringResource(R.string.release_date),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = releaseDate,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Thin,
                        color = Color.Gray
                    )
                }

                MyDividerHorizontal()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(start = 12.dp)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = stringResource(R.string.spoken_lang),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = spokenLang,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Thin,
                        color = Color.Gray
                    )
                }

                MyDividerHorizontal()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(start = 12.dp)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = stringResource(R.string.production_country),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = productionCountryText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Thin,
                        color = Color.Gray
                    )
                }

                MyDividerHorizontal()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(start = 12.dp)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = stringResource(R.string.origin_country),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Text(
                        text = originCountryText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Thin,
                        color = Color.Gray
                    )
                }


                MySpacerHeight(height = 8.dp)

            }

        }

    }
}