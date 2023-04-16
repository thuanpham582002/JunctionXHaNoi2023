package dev.keego.fintechass.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.screen.chat.components._pay_ment

@Composable
fun _card_select_message(
    onClickIntent: () -> Unit
) {
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

    _pay_ment(
        title = "Send Bill",
        intentIcon = state.payment!!.smallImage,
        intentDescription = "Nội dung\n" + "CK"
    ) {
        Box(
            modifier = Modifier.clickable(onClick = onClickIntent),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.message)
        }
    }
}