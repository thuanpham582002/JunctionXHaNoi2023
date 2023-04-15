package dev.keego.fintechass.screen.channellist.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
internal fun _new_chat_dialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Text(text = "New chat dialog")
    }
}