package dev.keego.fintechass.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class ButtonNextState {
    Next,
    Done
}

@Composable
fun _button_next(state: ButtonNextState = ButtonNextState.Next, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Red)
            .clickable(onClick = onClick),
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp),
            imageVector = if (state == ButtonNextState.Next) Icons.Default.ArrowForwardIos else Icons.Default.Check,
            contentDescription = null
        )
    }
}