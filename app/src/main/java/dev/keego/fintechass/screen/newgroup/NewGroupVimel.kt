package dev.keego.fintechass.screen.newgroup

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
class NewGroupVimel @Inject constructor(private val roomRepository: RoomRepository) :
    VimelStateHolder<NewGroupVimel.NewGroupState>(NewGroupState()) {
    data class NewGroupState(
        val name: String = "Group mới",
        val listUserId: List<Int> = listOf(),
    ) : State

    fun updateState(state: State) {
        update { state as NewGroupState }
    }

    fun insertRoomChat(roomChat: RoomChat) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertRoom(roomChat)
        }
    }
}