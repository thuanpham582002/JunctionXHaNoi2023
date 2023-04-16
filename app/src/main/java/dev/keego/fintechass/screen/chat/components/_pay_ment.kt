package dev.keego.fintechass.screen.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.ui.AssistantVimel

@Composable
internal fun _pay_ment(
    modifier: Modifier = Modifier,
    title: String,
    intentDescription: String,
    intentIcon: Int,
    amountComposable: @Composable BoxScope.() -> Unit
) {
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF9800), Color(0xFFE91E63)),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(100f, 100f)
                )
            )
            .padding(bottom = 8.dp, start = 8.dp)
    ) {
        Column {
            CompositionLocalProvider(LocalContentColor provides Color.White) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = title, modifier = Modifier.weight(1f))
                    IconButton(onClick = vimel::stopFlow) {
                        androidx.compose.material.Icon(imageVector = Icons.Default.Close, null)
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CompositionLocalProvider(LocalContentColor provides Color.White) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Text(
//                                text = "${state.receivers.size}",
//                                style = MaterialTheme.typography.headlineSmall
//                            )
//                            Spacer(modifier = Modifier.width(4.dp))
                            androidx.compose.material.Icon(
                                painter = painterResource(id = intentIcon),
                                null
                            )
                        }
                        Text(text = intentDescription)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    amountComposable()
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}