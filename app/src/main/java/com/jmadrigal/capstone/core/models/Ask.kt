package com.jmadrigal.capstone.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jmadrigal.capstone.utils.convertToCurrency

data class Ask(
    @SerializedName("amount")
    @Expose
    var amount: String,
    @SerializedName("book")
    @Expose
    var book: String,
    @SerializedName("price")
    @Expose
    var price: String
) {
    val formattedAmount: String
        get() = amount.convertToCurrency()

    val formattedPrice: String
        get() = price.convertToCurrency()
}
