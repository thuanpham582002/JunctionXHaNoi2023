package dev.keego.fintechass.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.screen.chat.components._pay_ment

@SuppressLint("ComposableNaming")
@Composable
fun _card_suggest(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

    Row(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF9800), Color(0xFFE91E63)),
                    start = Offset(0f, 0f),
                    end = Offset(100f, 100f)
                )
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = "Create splitting bill", color = Color.White)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null, tint = Color.White)
    }
}