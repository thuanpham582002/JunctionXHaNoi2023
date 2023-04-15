package dev.keego.fintechass.screen.channellist

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.keego.fintechass.R
import dev.keego.fintechass.screen.channellist.components._avatar_item
import dev.keego.fintechass.screen.channellist.components._channel_item
import dev.keego.fintechass.screen.channellist.components._empty_content
import dev.keego.fintechass.screen.chat.components._pay_ment
import dev.keego.fintechass.screen.destinations.chat_Destination
import dev.keego.fintechass.screen.destinations.new_group_Destination
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.listUserExample

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
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            _pay_ment(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                titleCompose = {
                    Text(text = "Transfer Money")
                }, intentCompose = {
                    Text(text = "Intent")
                }, amountCompose = {
                    Text(text = "Amount")
                })

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

