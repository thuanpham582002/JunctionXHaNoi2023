package dev.keego.fintechass.screen.channellist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.keego.fintechass.R

@Composable
fun _empty_content(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.6f),
            painter = painterResource(id = R.drawable.text_talk),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Let's start chatting!",
            modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = "Start Chatting",
                modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
