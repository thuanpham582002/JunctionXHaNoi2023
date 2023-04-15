package dev.keego.fintechass.setup.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import dev.keego.fintechass.setup.room.converter.MessageConverter


@Dao
interface RoomDao {
    @Insert
    suspend fun insertRoom(roomChat: RoomChat)

    @Query("SELECT * FROM roomTable")
    suspend fun getAllRoomChat(): List<RoomChat>

    @Query("DELETE FROM roomTable")
    suspend fun deleteAllRoomChat()

    @Query("DELETE FROM roomTable WHERE id = :id")
    suspend fun deleteRoomChat(id: Int)


    @Query("SELECT * FROM roomTable WHERE id = :id")
    suspend fun getRoomChat(id: Int): RoomChat

    @Query("UPDATE roomTable SET messages = :messages WHERE id = :id")
    @TypeConverters(MessageConverter::class)
    suspend fun addMessage(id: Int, messages: List<Message>)
}