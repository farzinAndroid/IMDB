package com.farzin.imdb.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.farzin.imdb.R
import com.farzin.imdb.ui.theme.imdbYellow
import com.farzin.imdb.ui.theme.strongGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MySpacerWidth(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun MySpacerHeight(height: Dp) {
    Spacer(modifier = Modifier.height(height))
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
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.imdbYellow,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
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


@Composable
fun ChangeStatusBarColor() {
    val systemUiController = rememberSystemUiController()

    val statusBarColor = if (isSystemInDarkTheme()) {
        Color.Black
    } else {
        Color.White
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor
        )
    }

}


@Composable
fun LoadingMovie() {

    if (isSystemInDarkTheme()) {
        val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading1))

        LottieAnimation(composition = lottie, iterations = LottieConstants.IterateForever)
    } else {
        val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading2))

        LottieAnimation(composition = lottie, iterations = LottieConstants.IterateForever)
    }


}

@Composable
fun LoadingDots() {

    if (isSystemInDarkTheme()) {
        val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading3dots))

        LottieAnimation(composition = lottie, iterations = LottieConstants.IterateForever)
    } else {
        val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading3dotsdark))

        LottieAnimation(composition = lottie, iterations = LottieConstants.IterateForever)
    }


}


@Composable
fun MyLoadingFullScreen(modifier: Modifier = Modifier) {

    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingMovie()
    }

}

@Composable
fun My3DotsLoading(modifier: Modifier = Modifier) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingDots()
    }
}
