package dev.keego.fintechass.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.R

@Composable
fun _card_select_confirm(
    onDone: () -> Unit,
    onChangeAmount: () -> Unit,
    onChangeReceiver: () -> Unit,
    onChangeContent: () -> Unit
) {
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

    Column {
        Row {
            Text(text = "Gửi lời mời chuyển tiền", modifier = Modifier.weight(1f))
            IconButton(onClick = vimel::stopFlow) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
        Column {
            Row(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Chuyển tiền cho")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "60.000đ")
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(Modifier.weight(1f)) {
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.im_viettel_money),
                        contentDescription = null
                    )
                    Text(text = "0823584671")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Text(text = "Đây là lời nhắn")
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "OK", color = Color.White)
        }
    }
}