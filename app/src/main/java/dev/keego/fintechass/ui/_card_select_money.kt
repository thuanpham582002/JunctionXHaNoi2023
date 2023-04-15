package dev.keego.fintechass.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.chat.components._pay_ment

@Composable
fun _card_select_money(amount: String) {
    _pay_ment(titleCompose = {
        Text(text = "Send Bill")
    }, intentCompose = {
        Column {
            Icon(painter = Icons.Default., null)
            Text(text = "Members")
        }
    }, amountCompose = {

    }
}