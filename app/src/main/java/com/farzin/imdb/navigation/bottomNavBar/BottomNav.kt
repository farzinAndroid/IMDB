package com.farzin.imdb.navigation.bottomNavBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.farzin.imdb.R
import com.farzin.imdb.navigation.Screens
import com.farzin.imdb.ui.theme.appBackGround
import com.farzin.imdb.ui.theme.selectedColor
import com.farzin.imdb.utils.MySpacerHeight

@Composable
fun BottomNav(
    navController: NavController,
    onItemClick:(BottomNavItem)->Unit
) {

    val bottomNavItems = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = Screens.Home.route,
            selectedColor = MaterialTheme.colorScheme.selectedColor,
            deSelectedColor = Color.Gray,
            icon = painterResource(R.drawable.home)
        ),
        BottomNavItem(
            name = stringResource(R.string.search),
            route = Screens.Search.route,
            selectedColor = MaterialTheme.colorScheme.selectedColor,
            deSelectedColor = Color.Gray,
            icon = painterResource(R.drawable.search)
        ),
        BottomNavItem(
            name = stringResource(R.string.you),
            route = Screens.Profile.route,
            selectedColor = MaterialTheme.colorScheme.selectedColor,
            deSelectedColor = Color.Gray,
            icon = painterResource(R.drawable.profile)
        ),
    )


    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar= backStackEntry.value?.destination?.route in bottomNavItems.map { it.route }


    if (showBottomBar){


       BottomAppBar(
           modifier = Modifier
               .fillMaxWidth()
               .height(60.dp),
           tonalElevation = 4.dp,
           containerColor = MaterialTheme.colorScheme.appBackGround
       ) {

          bottomNavItems.forEachIndexed { index, item ->

              val selected = item.route == backStackEntry.value?.destination?.route

              BottomNavigationItem(
                  selected = selected,
                  onClick = { onItemClick(item) },
                  icon = {
                      Column(
                          modifier = Modifier
                              .fillMaxSize(),
                          verticalArrangement = Arrangement.Center,
                          horizontalAlignment = CenterHorizontally
                      ) {

                          Icon(
                              painter = item.icon,
                              contentDescription = "",
                              modifier = Modifier
                                  .size(24.dp),
                              tint = if (selected) item.selectedColor else item.deSelectedColor,
                          )

                          MySpacerHeight(height = 4.dp)

                          Text(
                              text = item.name,
                              style = MaterialTheme.typography.titleSmall,
                              color = if (selected) item.selectedColor else item.deSelectedColor,
                              modifier = Modifier.fillMaxWidth(),
                              textAlign = TextAlign.Center
                          )

                      }
                  }
              )

          }

       }

    }


}