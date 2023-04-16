package dev.keego.fintechass.screen.channellist

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.keego.fintechass.R
import dev.keego.fintechass.destinations.chat_Destination
import dev.keego.fintechass.destinations.new_group_Destination
import dev.keego.fintechass.screen.channellist.components._avatar_item
import dev.keego.fintechass.screen.channellist.components._channel_item
import dev.keego.fintechass.screen.channellist.components._empty_content
import dev.keego.fintechass.screen.chat.components._pay_ment
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.listUserExample
import dev.keego.fintechass.ui._assistant_component

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun channel_list_(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val channelListVimel: ChannelListVimel = hiltViewModel()
    val state by channelListVimel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        channelListVimel.init()
    }

    Scaffold(topBar = {
        var expanded by remember { mutableStateOf(false) }
        CenterAlignedTopAppBar(title = { Text(text = "Chat") }, navigationIcon = {
            Box {
                IconButton(onClick = {
                    expanded = true
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.union),
                        contentDescription = null,
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    listUserExample.forEach {
                        if (it.id == 0) return@forEach
                        DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                            expanded = false
                            if (state.roomChats.find { roomChat ->
                                    roomChat.users == listOf(
                                        listUserExample[0], it
                                    )
                                } == null) {
                                channelListVimel.insertRoomChat(
                                    RoomChat(
                                        name = it.name,
                                        avt = it.avt,
                                        messages = listOf(),
                                        users = listOf(
                                            listUserExample[0], it
                                        )
                                    )
                                )
                            }

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
        }, actions = {
            IconButton(onClick = {
                navigator.navigate(new_group_Destination())
            }) {
                Image(
                    painter = painterResource(id = R.drawable.pen), contentDescription = null
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
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            _assistant_component()

            if (state.roomChats.isEmpty()) {
                _empty_content(Modifier.fillMaxSize()) {
                    navigator.navigate(new_group_Destination())
                }
            } else

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    listUserExample.forEach {
                        _avatar_item(it.avt)
                    }
                }
            state.roomChats.forEach {
                _channel_item(user = Pair(it.name, it.avt), lastMessage = try {
                    it.messages.last().message
                } catch (e: Exception) {
                    ""
                }, onClick = {
                    navigator.navigate(chat_Destination(it.id))
                })
            }
        }
    }
}

