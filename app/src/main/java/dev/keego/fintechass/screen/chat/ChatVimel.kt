package dev.keego.fintechass.screen.chat

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keego.fintechass.setup.room.Message
import dev.keego.fintechass.setup.room.RoomChat
import dev.keego.fintechass.setup.room.RoomRepository
import dev.keego.fintechass.state.State
import dev.keego.fintechass.state.VimelStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatVimel @Inject constructor(private val roomRepository: RoomRepository) :
    VimelStateHolder<ChatVimel.ChatState>(ChatState()) {

    data class ChatState(
        var roomChat: RoomChat? = null,
        var currentUserId: Int = 0,
    ) : State

    fun init(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val roomChat = roomRepository.getRoomChat(id)
            update {
                it.copy(roomChat = roomChat)
            }
        }
    }

    fun switchUser(id: Int) {
        update {
            it.copy(currentUserId = id)
        }
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            val roomChat = state.value.roomChat
            if (roomChat != null) {
                val messages = roomChat.messages.toMutableList()
                messages.add(message)
                update { it.copy(roomChat = roomChat.copy(messages = messages)) }
                roomRepository.addMessage(roomChat.id, messages)
            }
        }
    }
}