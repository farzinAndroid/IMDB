package com.farzin.imdb.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.R
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.utils.Constants
import com.farzin.imdb.utils.IMDBButton
import com.farzin.imdb.utils.MySpacerHeight
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginSection(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {


    val nameVal = remember { mutableStateOf("") }
    val passVal = remember { mutableStateOf("") }
    var sessionIdText by remember { mutableStateOf("") }
    var accIdText by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }


    val sessionResult by profileViewModel.sessionId.collectAsState()
    when (sessionResult) {
        is NetworkResult.Success -> {
            sessionResult.data?.session_id?.let { sessionId ->
                dataStoreViewModel.saveSessionId(sessionId)
                Constants.SESSION_ID = sessionId
                sessionIdText = sessionId
            }
        }
        is NetworkResult.Error -> {}
        is NetworkResult.Loading -> {}
    }


    val accountDetailResult by profileViewModel.accountDetail.collectAsState()
    when (accountDetailResult) {
        is NetworkResult.Success -> {
            accountDetailResult.data?.let {
                accIdText = it.id.toString()
                userName = it.username
                dataStoreViewModel.saveAccountId(accIdText)
                dataStoreViewModel.saveUserName(userName)
                Constants.ACC_ID = accIdText
                Constants.USER_NAME = userName
                profileViewModel.screenState = ProfileState.LOGGED
            }
        }

        is NetworkResult.Error -> {
            Log.e("TAG", "error")
        }

        is NetworkResult.Loading -> {}
    }









    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.unlock_all_that_imdb_has_to_offer),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Thin,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(2.dp)
                .clip(Shapes().small)
                .background(MaterialTheme.colorScheme.imdbYellow)
        )

        MySpacerHeight(height = 16.dp)


        CustomTextField(
            label = {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            },
            value = nameVal,
            onValueCallback = {
                nameVal.value = it
            }
        )

        MySpacerHeight(height = 16.dp)

        CustomTextField(
            label = {
                Text(
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            },
            value = passVal,
            onValueCallback = {
                passVal.value = it
            },
            isPasswordTextField = true

        )

        MySpacerHeight(height = 16.dp)

        IMDBButton(
            text =stringResource(R.string.login) ,
            onClick = {
                coroutineScope.launch {
                    profileViewModel.getSessionId(
                        userName = nameVal.value,
                        password = passVal.value,
                        requestToken = Constants.REQUEST_TOKEN
                    )

                }
            },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                focusedElevation = 4.dp,
            ),
            containerColor =MaterialTheme.colorScheme.imdbYellow,
            textColor = Color.Black,
            fontWeight =FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp),
            style =MaterialTheme.typography.bodyLarge

        )



    }


}