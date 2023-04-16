package dev.keego.fintechass.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.chat.components._pay_ment

@Composable
fun _card_select_payment(onClickIntent: () -> Unit) {
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

    _pay_ment(
        title = "Send bill", intentIcon = R.drawable.ic_savings_24,
        intentDescription = "Beneficiary\nAccount"
    ) {
        Box(
            modifier = Modifier.clickable(onClick = onClickIntent),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.im_viettel_money),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.7f),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}