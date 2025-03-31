package com.compose_demo.vishaldemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.compose_demo.vishaldemo.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(uiState: SplashUiState, interActor: SplashInterActor) {

    LaunchedEffect(true) {
        delay(500)
        interActor.navigateToNext()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(108.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(top = 24.dp),
                color = Color.Black
            )
        }
    }

}