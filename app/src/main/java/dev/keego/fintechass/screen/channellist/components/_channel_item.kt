package dev.keego.fintechass.screen.channellist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun _channel_item(user: Pair<String, Int>, lastMessage: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(4.dp)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        _avatar_item(avtId = user.second)

        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Text(text = user.first, style = MaterialTheme.typography.labelLarge)
            Text(text = lastMessage, style = MaterialTheme.typography.bodySmall)
        }
    }
}
