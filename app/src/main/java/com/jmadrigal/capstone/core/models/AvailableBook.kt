package com.jmadrigal.capstone.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailableBook(
    @SerializedName("book")
    @Expose
    var book: String,
    @SerializedName("minimum_amount")
    @Expose
    var minimumAmount: String,
    @SerializedName("maximum_amount")
    @Expose
    var maximumAmount: String,
    @SerializedName("minimum_price")
    @Expose
    var minimumPrice: String,
    @SerializedName("maximum_price")
    @Expose
    var maximumPrice: String,
    @SerializedName("minimum_value")
    @Expose
    var minimumValue: String,
    @SerializedName("maximum_value")
    @Expose
    var maximumValue: String
)
