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
import dev.keego.fintechass.setup.room.UserDatabase
import dev.keego.fintechass.setup.room.UserRepository
import dev.keego.fintechass.setup.room.converter.ListIntConverter
import dev.keego.fintechass.setup.room.converter.MessageConverter
import dev.keego.fintechass.setup.room.converter.PaymentCardConverter
import dev.keego.fintechass.setup.room.converter.UserConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Provides
    @Singleton
    fun provideRoomRepository(@ApplicationContext context: Context): RoomRepository {
        val roomDatabase: RoomDatabase = Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "message-db-v1"
        ).addTypeConverter(MessageConverter())
            .addTypeConverter(UserConverter())
            .build()
        return RoomRepository(roomDatabase.roomDao())
    }

    @Provides
    @Singleton
    fun provideUserRepository(@ApplicationContext context: Context): UserRepository {
        val roomDatabase: UserDatabase = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user-db-v1"
        ).addTypeConverter(PaymentCardConverter())
            .addTypeConverter(ListIntConverter())
            .addTypeConverter(PaymentCardConverter())
            .build()
        return UserRepository(roomDatabase.userDao())
    }
}