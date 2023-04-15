package dev.keego.fintechass.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.chat.components._pay_ment

@Composable
fun _card_select_member(avatars: List<Int>, onClickIntent: () -> Unit) {
    val step = 16.dp
    _pay_ment(titleCompose = {
        Text(text = "Send Bill")
    }, intentCompose = {
        Column {
            Row {
                Text(text = "${avatars.size}")
                Icon(painter = painterResource(id = R.drawable.ic_people), null)
            }
            Text(text = "Members")
        }
    }, amountCompose = {
        Box(
            modifier = Modifier
                .background(Color.White)
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClickIntent)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box {
                avatars.take(4).forEachIndexed { index, imageId ->
                    _avatar(src = imageId, modifier = Modifier.padding(start = step * index))
                }
                if (avatars.size > 4) {
                    _avatar(
                        src = R.drawable.ic_three_dots,
                        modifier = Modifier.padding(start = step * 4),
                    )
                }
            }
        }
    })
}

@Composable
private fun _avatar(modifier: Modifier = Modifier, size: Dp = 32.dp, @DrawableRes src: Int) {
    Image(painter = painterResource(id = src), contentDescription = null)
}