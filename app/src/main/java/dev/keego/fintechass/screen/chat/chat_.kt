package dev.keego.fintechass.screen.chat

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.chat.components._message_item_me
import dev.keego.fintechass.screen.chat.components._message_item_you
import dev.keego.fintechass.setup.room.Message
import dev.keego.fintechass.setup.room.listUserExample
import dev.keego.fintechass.ui.AssistantVimel
import dev.keego.fintechass.ui._assistant_component
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun chat_(
    navigator: DestinationsNavigator, id: Int
) {
    val context = LocalContext.current
    val chatVimel: ChatVimel = hiltViewModel()
    val state by chatVimel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    LaunchedEffect(true) {
        chatVimel.init(id)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            val text = ""
            Text(
                text = if (state.roomChat != null) {
                    if (state.roomChat!!.users.size <= 2) {
                        listUserExample[state.currentUserId].name
                    } else {
                        state.roomChat!!.name
                    }
                } else {
                    "Loading..."
                }
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navigator.popBackStack()
            }) {
                Image(
                    imageVector = Icons.Default.ArrowBack, contentDescription = null
                )
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    LazyColumn(state = scrollState,
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                        content = {
                            state.roomChat?.messages?.size?.let { size ->
                                items(size) {
                                    if (state.roomChat!!.messages[it].idUser == state.currentUserId) {
                                        _message_item_me(state.roomChat!!.messages[it].message)
                                    } else {
                                        if (it > 0 && state.roomChat!!.messages[it].idUser == state.roomChat!!.messages[it - 1].idUser) _message_item_you(
                                            state.roomChat!!.messages[it].message,
                                            state.roomChat!!.messages[it].idUser,
                                        )
                                        else {
                                            _message_item_you(
                                                state.roomChat!!.messages[it].message,
                                                state.roomChat!!.messages[it].idUser,
                                                true
                                            )
                                        }

                                    }
                                    Divider(
                                        modifier = Modifier.height(8.dp),
                                        color = MaterialTheme.colorScheme.background
                                    )
                                }
                            }
                        })
                }
                _assistant_component(modifier = Modifier.align(Alignment.BottomEnd))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var expanded by remember { mutableStateOf(false) }

                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_more),
                        contentDescription = null
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        state.roomChat?.users?.forEach {
                            if (it.id == state.currentUserId) return@forEach
                            DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                                expanded = false
                                chatVimel.switchUser(it.id)
                                Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                            }, leadingIcon = {
                                Image(
                                    painter = painterResource(id = it.avt),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            })
                        }
                    }
                }

                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(24.dp),
                )

                val scope = rememberCoroutineScope()
                val assistantVimel: AssistantVimel = hiltViewModel()
                val assistantState by assistantVimel.state.collectAsState()

                IconButton(onClick = {
                    if (text.isEmpty()) return@IconButton
                    chatVimel.insertMessage(
                        Message(state.currentUserId, text.trim()),
                        onEntityReceive = {
                            assistantVimel.setEntity(it)
                            if (assistantState.amount.isNotEmpty()) {
                                chatVimel.insertMessage(
                                    Message(
                                        listUserExample[1].id,
                                        "Bạn muốn chuyển khoản cho tk ${
                                            assistantState
                                                .accountNumber
                                        }\n" + "Ngân hàng: ${
                                            assistantState
                                                .payment?.name
                                        }\n" + "Số tiền: ${assistantState.amount}\n "
                                    )
                                ) {}
                            }
                        })
                    state.roomChat?.messages?.size?.let {
                        if (it > 0) {
                            scope.launch {
                                scrollState.animateScrollToItem(it - 1)
                            }
                        }
                    }
                    text = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_send),
                        contentDescription = null
                    )
                }
            }
        }
    }
}