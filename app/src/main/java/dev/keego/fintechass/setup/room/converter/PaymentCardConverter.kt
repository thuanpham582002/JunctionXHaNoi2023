package dev.keego.fintechass.setup.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.keego.fintechass.setup.room.PaymentCard

@ProvidedTypeConverter
class PaymentCardConverter {
    @TypeConverter
    fun fromString(value: String): PaymentCard {
        val listType = object : TypeToken<PaymentCard>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: PaymentCard): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}