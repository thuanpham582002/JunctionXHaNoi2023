package dev.keego.fintechass.setup.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.keego.fintechass.setup.room.converter.MessageConverter
import dev.keego.fintechass.setup.room.converter.UserConverter

@Entity(tableName = "roomTable")
data class RoomChat(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "avt") val avt: Int,
    @TypeConverters(MessageConverter::class) @ColumnInfo(name = "messages") val messages: List<Message> = listOf(),
    @TypeConverters(UserConverter::class) @ColumnInfo(name = "user") val users: List<User>,
)