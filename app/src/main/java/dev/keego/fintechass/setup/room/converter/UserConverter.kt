package dev.keego.fintechass.setup.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.keego.fintechass.setup.room.User

@ProvidedTypeConverter
class UserConverter {
    @TypeConverter
    fun fromString(value: String): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<User>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}