package dev.keego.fintechass.screen.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.keego.fintechass.setup.room.listUserExample

@Composable
internal fun _message_item_you(message: String, userId: Int = 0, isShowImage: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        if (isShowImage) {
            Image(
                painter = painterResource(id = listUserExample[userId].avt),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(50))
            )
        } else {
            Spacer(modifier = Modifier.size(30.dp))
        }


        Column(Modifier.weight(3f)) {
            if (isShowImage) {
                Text(
                    text = listUserExample[userId].name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xffe4e6eb))

            ) {

                Text(text = message, modifier = Modifier.padding(10.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}