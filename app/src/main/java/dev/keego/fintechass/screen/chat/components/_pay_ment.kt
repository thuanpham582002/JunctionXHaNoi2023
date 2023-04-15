package dev.keego.fintechass.screen.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
internal fun _pay_ment(
    modifier: Modifier = Modifier,
    titleCompose: @Composable RowScope.() -> Unit,
    intentCompose: @Composable ColumnScope.() -> Unit,
    amountCompose: @Composable () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFF9800), Color(0xFFE91E63)),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(100f, 100f)
                )
            )
            .padding(16.dp)
    ) {
        val (title, intent, amount, close_btn) = createRefs()
        Box(modifier = Modifier
            .constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.bodySmall.copy(
                    color = Color.White
                )
            ) {
                Row(content = titleCompose)
            }
        }

        Box(modifier = Modifier
            .constrainAs(close_btn) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.White)
        }

        Box(modifier = Modifier
            .constrainAs(intent) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.bodySmall.copy(
                    color = Color.White
                )
            ) {
                Column(content = intentCompose)
            }
        }

        Box(modifier = Modifier
            .constrainAs(amount) {
                start.linkTo(intent.end)
                top.linkTo(title.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(8.dp)
        ) {
            amountCompose()
        }
    }
}