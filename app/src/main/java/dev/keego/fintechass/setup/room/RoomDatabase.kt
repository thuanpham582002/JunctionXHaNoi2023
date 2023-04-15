package dev.keego.fintechass.setup.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.keego.fintechass.setup.room.converter.MessageConverter
import dev.keego.fintechass.setup.room.converter.UserConverter

@Database(entities = [(RoomChat::class)], version = 2, exportSchema = false)
@TypeConverters(MessageConverter::class, UserConverter::class)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}