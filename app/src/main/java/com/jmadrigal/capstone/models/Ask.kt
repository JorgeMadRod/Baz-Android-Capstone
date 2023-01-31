package com.jmadrigal.capstone.models

import com.google.gson.annotations.SerializedName

data class Ask(
    @SerializedName("amount")
    val amount : String,
    @SerializedName("book")
    val book : String,
    @SerializedName("price")
    val price : String
)
