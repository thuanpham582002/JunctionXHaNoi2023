package dev.keego.fintechass.setup.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import dev.keego.fintechass.setup.room.converter.ListIntConverter
import dev.keego.fintechass.setup.room.converter.MessageConverter


@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM userTable")
    suspend fun getAllUser(): List<User>

    @Query("DELETE FROM userTable")
    suspend fun deleteAllUser()

    @Query("DELETE FROM userTable WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("SELECT * FROM userTable WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Query("UPDATE userTable SET history_transaction = :historyTransaction WHERE id = :id")
    @TypeConverters(ListIntConverter::class)
    suspend fun updateHistoryTransaction(id: Int, historyTransaction: List<Int>)
}