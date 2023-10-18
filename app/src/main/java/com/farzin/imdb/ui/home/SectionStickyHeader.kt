package com.farzin.imdb.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.imdb.ui.theme.Cyan
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.utils.MySpacerWidth

@Composable
fun SectionStickyHeader(
    headerTitle:String,
    isHaveAnotherText : Boolean = false,
    headerSubtitle:String = "",
    headerOnClick:()->Unit = {}
) {

    Column {

        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .padding(top = 8.dp)
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {


                Box(
                    modifier = Modifier
                        .width(5.dp)
                        .height(50.dp)
                        .padding(vertical = 4.dp)
                        .clip(Shapes().small)
                        .background(MaterialTheme.colorScheme.imdbYellow)

                )

                MySpacerWidth(width = 12.dp)

                Text(
                    text = headerTitle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.normalText
                )





            }

            if (isHaveAnotherText){

                Text(
                    text = headerSubtitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.Cyan,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { headerOnClick() }
                )

            }


        } 
        
        

        
    }
    
  

}