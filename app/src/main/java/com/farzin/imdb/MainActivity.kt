package com.farzin.imdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.farzin.imdb.navigation.bottomNavBar.BottomNav
import com.farzin.imdb.navigation.setupNavgraph.SetUpNavGraph
import com.farzin.imdb.ui.screens.profile.InitialRequestToken
import com.farzin.imdb.ui.theme.IMDBTheme
import com.farzin.imdb.utils.AppConfig
import com.farzin.imdb.utils.ChangeStatusBarColor
import com.farzin.imdb.utils.Constants.ENGLISH
import com.farzin.imdb.utils.Constants.USER_LANG
import com.farzin.imdb.utils.LocaleUtils
import com.farzin.imdb.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val vm = viewModels<DataStoreViewModel>()

                    ChangeStatusBarColor()

                    InitialRequestToken()
                    AppConfig()

                    Log.e("TAG","session id ${vm.value.getSessionId()}")
                    Log.e("TAG","acc id ${vm.value.getAccountId()}")
                    Log.e("TAG", USER_LANG)

                    LocaleUtils.setLocale(LocalContext.current,USER_LANG)

                    val direction = if (USER_LANG == ENGLISH) {
                        LayoutDirection.Ltr
                    } else {
                        LayoutDirection.Rtl
                    }

                    CompositionLocalProvider(LocalLayoutDirection.provides(direction)) {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize(),
                            bottomBar = {
                                BottomNav(
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route)
                                    }
                                )
                            },
                            content = {
                                SetUpNavGraph(navController = navController)
                            }
                        )
                    }




                }
            }
        }
    }
}
