package dev.keego.fintechass.setup.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getAllUser(): List<User> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUser()
        }
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

    suspend fun deleteUser(id: Int) {
        userDao.deleteUser(id)
    }

    suspend fun getUser(id: Int): User {
        return withContext(Dispatchers.IO) {
            userDao.getUser(id)
        }
    }

    suspend fun updateHistoryTransaction(id: Int, historyTransaction: List<Int>) {
        userDao.updateHistoryTransaction(id, historyTransaction)
    }
}