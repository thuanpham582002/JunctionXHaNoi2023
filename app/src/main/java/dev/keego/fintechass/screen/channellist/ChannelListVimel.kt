package dev.keego.fintechass.screen.channellist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.RoomRepository
import dev.keego.fintechass.state.State
import dev.keego.fintechass.state.VimelStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelListVimel @Inject constructor(private val roomRepository: RoomRepository) :
    VimelStateHolder<ChannelListVimel.ChannelListState>(ChannelListState()) {
    data class ChannelListState(
        var roomChats: List<RoomChat> = listOf(),
    ) : State


    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val messages = roomRepository.getAllRoomChat()
            update {
                it.copy(roomChats = messages)
            }
        }
    }

    fun insertRoomChat(roomChat: RoomChat) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertRoom(roomChat)
            update {
                it.copy(roomChats = it.roomChats.toMutableList().apply {
                    add(roomChat)
                })
            }
        }
    }
}
