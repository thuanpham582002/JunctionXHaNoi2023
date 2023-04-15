package dev.keego.fintechass.setup.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.keego.fintechass.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentCard(
    val cardNumber: String = "",
    val bankName: String = "",
) : Parcelable

@Entity(tableName = "userTable")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "avt") val avt: Int,
    @ColumnInfo(name = "payment_card_number") val paymentCardNumber: PaymentCard = PaymentCard(),
) : Parcelable

val listUserExample = listOf<User>(
    User(0, "John", R.drawable._1),
    User(1, "Jane", R.drawable._2),
    User(2, "Jack", R.drawable._3),
    User(3, "Jill", R.drawable._4),
    User(4, "Jenny", R.drawable._5),
    User(5, "Jen", R.drawable._6),
    User(6, "Jenifer", R.drawable._7),
    User(7, "Terry", R.drawable._8),
    User(8, "Tom", R.drawable._9),
)

@Parcelize
data class Message(
    val idUser: Int,
    val message: String,
) : Parcelable

