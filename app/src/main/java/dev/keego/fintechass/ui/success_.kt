package dev.keego.fintechass.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun success_() {
    Scaffold {
        Column(Modifier.padding(it)) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = Color.Green
            )
            Text(text = "Thanh toán thành công")
            Spacer(modifier = Modifier.height(64.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    Text(text = "Người nhận", modifier = Modifier.weight(1f))
                    Text(text = "NGUYEN VIET QUANG")
                }
                Divider(
                    Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Row {
                    Text(text = "Ngân hàng thụ hưởng", modifier = Modifier.weight(1f))
                    Text(text = "TECHCOMBANK")
                }
                Divider(
                    Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Row {
                    Text(text = "Thành tiền", modifier = Modifier.weight(1f))
                    Text(text = "60.000đ")
                }
                Divider(
                    Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Row {
                    Text(text = "Nội dung", modifier = Modifier.weight(1f))
                    Text(text = "Quang chuyen tien banh SN")
                }
                Divider(
                    Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.Red,
                    textDecoration = TextDecoration.Underline,
                    text = "Có gì đó không ổn ?"
                )
            }
            Text(text = "5s nữa quay lại chat...")
        }
    }
}