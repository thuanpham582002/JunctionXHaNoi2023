package dev.keego.fintechass.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.chat.components._pay_ment

//@Composable
//fun _card_select_member(onClickIntent: () -> Unit) {
//    val vimel: AssistantVimel = hiltViewModel()
//    val state by vimel.state.collectAsState()
//
//    val step = 28.dp
//
//    _pay_ment(
//        title = "Send bill",
//        intentIcon = R.drawable.ic_people,
//        intentDescription = "Members"
//    ) {
//        Box(modifier = Modifier.padding(vertical = 16.dp).clickable(onClick = onClickIntent)) {
//            state.receivers.take(4).forEachIndexed { index, receiver ->
//                _avatar(src = receiver.avt, modifier = Modifier.padding(start = step * index))
//            }
//            if (state.receivers.size > 4) {
//                _avatar(
//                    src = R.drawable.ic_three_dots,
//                    modifier = Modifier.padding(start = step * 4),
//                )
//            }
//            if (state.receivers.isEmpty()) {
//                Text(text = "Select members", modifier = Modifier.fillMaxSize())
//            }
//        }
//    }
//}

@Composable
private fun _avatar(modifier: Modifier = Modifier, size: Dp = 48.dp, @DrawableRes src: Int) {
    Image(
        painter = painterResource(id = src),
        contentDescription = null,
        modifier = modifier.clip(CircleShape).size(size),
        contentScale = ContentScale.FillHeight
    )
}