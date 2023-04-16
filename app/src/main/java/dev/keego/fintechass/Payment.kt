package dev.keego.fintechass

import androidx.annotation.DrawableRes

data class Payment(
    val name: String,
    @DrawableRes val bigImage: Int,
    @DrawableRes val smallImage: Int,
) {
    companion object {
        fun getPayments(): List<Payment> {
            return listOf(
                Payment(
                    "Viettel Money",
                    R.drawable.im_viettel_money,
                    R.drawable.im_viettel_money_smol
                ),
            )
        }
    }
}