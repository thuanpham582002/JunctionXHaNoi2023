package dev.keego.fintechass.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.keego.fintechass.R

enum class AssistantState {
    NULL, SUGGEST, MEMBERS, AMOUNT, ACCOUNT, MESSAGE, CONFIRM
}

@Composable
fun _assistant_component(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val vimel: AssistantVimel = hiltViewModel()
    val state by vimel.state.collectAsState()

//    if (state.assistantState == AssistantState.NULL) {
    if (true) {
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = vimel::moveNext) {
                Image(painterResource(id = R.drawable.ic_magic), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (state.assistantState == AssistantState.SUGGEST) {
                Toast.makeText(
                    context,
                    "BankName: ${state.payment?.name ?: "Not found"}\n" + "AccountNumber: ${
                        state.accountNumber.ifEmpty { "Not found" }
                    }\n" + "Amount: ${
                        state.amount.ifEmpty { "Not found" }
                    }\n",
                    Toast.LENGTH_LONG
                ).show()
            }
            // I want to tranfer 20 to account number 2439393 at tcb
//                AssistantState.NULL -> {
//                }
//                AssistantState.SUGGEST -> {
//                    _card_suggest(onClick = vimel::moveNext)
//                }
//                AssistantState.MEMBERS -> {
//                    _card_select_member {
//                        /*TODO: Navigate to select member*/
//                    }
//                }
//                AssistantState.AMOUNT -> {
//                    _card_select_money {
//                        /*TODO: Navigate to select money*/
//                    }
//                }
//                AssistantState.ACCOUNT -> {
//                    _card_select_payment {
//                        /*TODO: Navigate to select payment*/
//                    }
//                }
//                AssistantState.MESSAGE -> {
//                    _card_select_message {
//
//                    }
//                }
//                AssistantState.CONFIRM -> {
//                    _card_select_confirm(
//                        onDone = { /*TODO*/ },
//                        onChangeAmount = { /*TODO*/ },
//                        onChangeReceiver = { /*TODO*/ }) {
//
//                    }
//                }
            if (listOf(
                    AssistantState.ACCOUNT,
                    AssistantState.MEMBERS,
                    AssistantState.AMOUNT,
                ).contains(state.assistantState)
            ) {
                _button_next(onClick = vimel::moveNext)
            } else if (state.assistantState != AssistantState.SUGGEST) _button_next(
                state = ButtonNextState.Done, onClick = vimel::submit
            )
        }
    }
}