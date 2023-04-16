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
                    "viettel",
                    R.drawable.im_viettel_money,
                    R.drawable.im_viettel_money_smol,
                ),
                Payment(
                    "techcombank",
                    R.drawable.im_tech,
                    R.drawable.im_tech,
                ),
                Payment(
                    "sacombank",
                    R.drawable.im_tech,
                    R.drawable.im_tech,
                ),
                Payment(
                    "Shopee Pay",
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

        fun find(name: String): Payment? {
            return getPayments().find { it.name == name }
        }
    }
}