package dev.keego.fintechass.setup.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(private val roomDao: RoomDao) {

    suspend fun getAllRoomChat(): List<RoomChat> {
        return withContext(Dispatchers.IO) {
            roomDao.getAllRoomChat()
        }
    }

    suspend fun insertRoom(roomChat: RoomChat) {
        roomDao.insertRoom(roomChat)
    }

    suspend fun deleteAllRoomChat() {
        roomDao.deleteAllRoomChat()
    }

    suspend fun deleteRoomChat(id: Int) {
        roomDao.deleteRoomChat(id)
    }

    suspend fun getRoomChat(id: Int): RoomChat {
        return withContext(Dispatchers.IO) {
            roomDao.getRoomChat(id)
        }
    }

    suspend fun addMessage(id: Int, messages: List<Message>) {
        roomDao.addMessage(id, messages)
    }
}