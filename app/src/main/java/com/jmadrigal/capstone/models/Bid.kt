package com.jmadrigal.capstone.models

import com.google.gson.annotations.SerializedName

data class Bid(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("book")
    val book: String,
    @SerializedName("oid")
    val oid: String,
    @SerializedName("price")
    val price: String
)