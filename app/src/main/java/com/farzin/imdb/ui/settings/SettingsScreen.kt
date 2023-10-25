package com.farzin.imdb.ui.settings

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.imdb.MainActivity
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.ui.profile.ProfileState
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.normalText
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.IMDBButton
import com.farzin.imdb.utils.MyDividerHorizontal
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    ) {

    val scope = rememberCoroutineScope()

    val radioList = listOf(
        stringResource(R.string.persian),
        stringResource(R.string.english)
    )

    val (selectedOption, onOptionSelected) = remember {
        if (Constants.USER_LANG == Constants.PERSIAN)
            mutableStateOf(radioList[0])
        else
            mutableStateOf(radioList[1])
    }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Text(
            text = stringResource(R.string.language),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.normalText,
            fontWeight = FontWeight.Thin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Start
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            radioList.forEachIndexed { index, text ->

                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.normalText
                )

                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {

                        onOptionSelected(text)

                        if (text == context.getString(R.string.persian)) {
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                            dataStoreViewModel.saveUserLang(Constants.PERSIAN)
                            Constants.USER_LANG = Constants.PERSIAN

                            context.apply {
                                finish()
                                startActivity(Intent(context, MainActivity::class.java))
                            }

                        } else {
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                            dataStoreViewModel.saveUserLang(Constants.ENGLISH)
                            Constants.USER_LANG = Constants.ENGLISH

                            context.apply {
                                finish()
                                startActivity(Intent(context, MainActivity::class.java))
                            }
                        }


                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.imdbYellow,
                        unselectedColor = MaterialTheme.colorScheme.normalText,
                        disabledUnselectedColor = MaterialTheme.colorScheme.normalText,
                    )
                )

            }

        }

        MyDividerHorizontal()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IMDBButton(
                text = stringResource(R.string.logout),
                onClick = {
                    dataStoreViewModel.saveSessionId("")
                    Constants.SESSION_ID = ""
                    dataStoreViewModel.saveAccountId("")
                    Constants.ACC_ID = ""
                    profileViewModel.screenState = ProfileState.NOTLOGGED
                    scope.launch {
                        profileViewModel.getInitialRequestToken()

                        profileViewModel.initialRequestToken.collectLatest { reqToken ->
                            when (reqToken) {
                                is NetworkResult.Success -> {
                                    reqToken.data?.request_token?.let {
                                        Constants.REQUEST_TOKEN = it
                                        Log.e("TAG","this id from setting composable $it")
                                    }
                                }
                                else->{}
                            }
                        }


                    }
                    navController.popBackStack()

                }
            )
        }


    }

}