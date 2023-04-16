package dev.keego.fintechass.screen.newgroup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.listUserExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun new_group_(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val newGroupVimel: NewGroupVimel = hiltViewModel()
    val state by newGroupVimel.state.collectAsState()
    var listCheckBox by remember {
        mutableStateOf(
            listOf(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
            )
        )
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = state.name
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Image(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val listUser = listCheckBox.mapIndexed { index, b ->
                            if (b) listUserExample[index] else null
                        }.filterNotNull()
                        val roomChat = RoomChat(
                            name = state.name,
                            avt = R.drawable.cosplay,
                            users = listUser
                        )
                        newGroupVimel.insertRoomChat(roomChat)
                        Toast.makeText(context, "Add room chat success", Toast.LENGTH_SHORT).show()
                        navigator.popBackStack()
                    }) {
                        Image(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = state.name,
                onValueChange = { newText ->
                    newGroupVimel.updateState(state.copy(name = newText))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(24.dp),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                listUserExample.forEach {
                    if (it.id == 0) return@forEach
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = it.avt),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(text = it.name)
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(
                                checked = listCheckBox[it.id],
                                onCheckedChange = { checked ->
                                    listCheckBox = listCheckBox.toMutableList().apply {
                                        set(it.id, checked)
                                    }
                                })
                        }
                        Divider()
                    }
                }
            }
        }
    }
}