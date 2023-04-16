package dev.keego.fintechass

import dev.keego.fintechass.setup.room.PaymentCard
import dev.keego.fintechass.setup.room.User

object FakeData {
    val payments = listOf(
        Payment(
            "Viettel Money",
            R.drawable.im_viettel_money,
            R.drawable.im_viettel_money_smol,
        ),
        Payment(
            "Techcombank",
            R.drawable.im_tech,
            R.drawable.im_tech,
        ),
        Payment(
            "ShopeePay",
            R.drawable.im_shopee,
            R.drawable.im_shopee,
        ),
        Payment(
            "Visa",
            R.drawable.im_visa,
            R.drawable.im_visa,
        )
    )
}