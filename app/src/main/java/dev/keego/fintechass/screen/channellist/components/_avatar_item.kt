package dev.keego.fintechass.screen.channellist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.keego.fintechass.R

@Composable
fun _avatar_item(avtId: Int) {
    Box {
        Image(
            painter = painterResource(id = avtId),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .padding(6.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.online),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .border(2.dp, Color.White, CircleShape)

            )
        }
    }
}