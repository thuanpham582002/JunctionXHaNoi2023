package dev.keego.fintechass.setup.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.keego.fintechass.setup.room.Message

@ProvidedTypeConverter
class MessageConverter {
    @TypeConverter
    fun fromString(value: String): List<Message> {
        val listType = object : TypeToken<List<Message>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Message>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
