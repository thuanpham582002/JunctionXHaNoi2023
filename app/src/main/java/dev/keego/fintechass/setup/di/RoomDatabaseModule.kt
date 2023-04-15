package dev.keego.fintechass.setup.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.keego.fintechass.setup.room.RoomDatabase
import dev.keego.fintechass.setup.room.RoomRepository
import dev.keego.fintechass.setup.room.converter.MessageConverter
import dev.keego.fintechass.setup.room.converter.UserConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "message-db-v1"
        ).addTypeConverter(MessageConverter())
            .addTypeConverter(UserConverter())
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(roomDatabase: RoomDatabase) = RoomRepository(roomDatabase.roomDao())
}