package dev.keego.fintechass.setup.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.keego.fintechass.R
import dev.keego.fintechass.setup.room.converter.ListIntConverter
import dev.keego.fintechass.setup.room.converter.PaymentCardConverter
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
    @TypeConverters(PaymentCardConverter::class) @ColumnInfo(name = "payment_card_number") val paymentCardNumber:
    PaymentCard = PaymentCard(),
    @TypeConverters(ListIntConverter::class) @ColumnInfo(name = "history_transaction") val
    historyTransaction: List<Int> =
        listOf(),
) : Parcelable


val listUserExample = listOf<User>(
    User(0, "John", R.drawable._1, PaymentCard("12345678934", "vietcombank")),
    User(1, "Jane", R.drawable._2, PaymentCard("12248323443", "techcombank")),
    User(2, "Jack", R.drawable._3, PaymentCard("12345348934", "vietcombank")),
    User(3, "Jill", R.drawable._4, PaymentCard("12248323434", "techcombank")),
    User(4, "Jenny", R.drawable._5, PaymentCard("0375556883", "viettel")),
    User(5, "Jen", R.drawable._6, PaymentCard("122483234", "techcombank")),
    User(6, "Jenifer", R.drawable._7, PaymentCard("0375556882", "viettel")),
    User(7, "Terry", R.drawable._8, PaymentCard("0374194245", "viettel")),
    User(8, "Tom", R.drawable._9, PaymentCard("12248323434", "sacombank")),
    User(9, "Adam", R.drawable._10, PaymentCard("12345678901", "vietcombank")),
    User(10, "Eve", R.drawable._10, PaymentCard("12248323111", "techcombank")),
    User(11, "David", R.drawable._10, PaymentCard("12345348111", "vietcombank")),
    User(12, "Sarah", R.drawable._10, PaymentCard("12248323113", "techcombank")),
    User(13, "Peter", R.drawable._10, PaymentCard("0375556884", "viettel")),
    User(14, "Paul", R.drawable._10, PaymentCard("122483235", "techcombank")),
    User(15, "Mary", R.drawable._10, PaymentCard("0375556885", "viettel")),
    User(16, "Lucy", R.drawable._10, PaymentCard("0374194246", "viettel")),
    User(17, "Mike", R.drawable._10, PaymentCard("12248323114", "sacombank")),
    User(18, "Sophia", R.drawable._10, PaymentCard("12345678918", "agribank")),
    User(19, "William", R.drawable._10, PaymentCard("12248323020", "bidv")),
    User(20, "Olivia", R.drawable._10, PaymentCard("12345348220", "agribank")),
    User(21, "James", R.drawable._10, PaymentCard("12248323222", "vietinbank")),
    User(22, "Emily", R.drawable._10, PaymentCard("0375556887", "mbbank")),
    User(23, "Benjamin", R.drawable._10, PaymentCard("12248323424", "vietinbank")),
    User(24, "Mia", R.drawable._10, PaymentCard("0375556888", "mbbank")),
    User(25, "Jacob", R.drawable._10, PaymentCard("0374194249", "acb")),
    User(26, "Emma", R.drawable._10, PaymentCard("12248323427", "techcombank")),
)


@Parcelize
data class Message(
    val idUser: Int,
    val message: String,
) : Parcelable

