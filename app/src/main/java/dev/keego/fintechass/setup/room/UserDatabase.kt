package dev.keego.fintechass.setup.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.keego.fintechass.setup.room.converter.ListIntConverter
import dev.keego.fintechass.setup.room.converter.PaymentCardConverter

@Database(entities = [(User::class)], version = 1, exportSchema = false)
@TypeConverters(PaymentCardConverter::class, ListIntConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}