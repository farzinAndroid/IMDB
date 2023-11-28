package com.farzin.imdb.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.ui.theme.darkText
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.strongGray
import com.farzin.imdb.viewmodel.DataStoreViewModel
import com.farzin.imdb.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun MySpacerWidth(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun MySpacerHeight(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun InitialRequestToken(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel= hiltViewModel(),
) {

    var loading by remember { mutableStateOf(false) }

    if (dataStoreViewModel.getSessionId().isNullOrEmpty() || dataStoreViewModel.getAccountId().isNullOrEmpty()){
        LaunchedEffect(true) {
            profileViewModel.getInitialRequestToken()

            profileViewModel.initialRequestToken.collectLatest { reqToken ->
                when (reqToken) {
                    is NetworkResult.Success -> {
                        loading = false
                        reqToken.data?.request_token?.let {
                            Constants.REQUEST_TOKEN = it
                            Log.e("TAG","this id from composable $it")
                        }
                    }

                    is NetworkResult.Error -> {
                        loading = false
                    }

                    is NetworkResult.Loading -> {
                        loading = true
                    }
                }
            }


        }
    }



}

@Composable
fun MyDividerHorizontal(modifier: Modifier = Modifier) {

    Divider(
        color = MaterialTheme.colorScheme.strongGray,
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    )

}
@Composable
fun MyDividerVertical(modifier: Modifier = Modifier) {

    Divider(
        color = MaterialTheme.colorScheme.strongGray,
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
    )

}


@Composable
fun IMDBButton(
    text:String,
    onClick:()->Unit,
    containerColor:Color = MaterialTheme.colorScheme.imdbYellow,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.darkText,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(2.dp)
) {

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor),
        elevation = elevation,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = style,
            color = textColor,
            fontWeight = fontWeight,
        )
    }

}