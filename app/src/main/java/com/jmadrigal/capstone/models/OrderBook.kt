package com.jmadrigal.capstone.models

import com.google.gson.annotations.SerializedName

data class OrderBook(
    @SerializedName("asks")
    val asks: List<Ask>,
    @SerializedName("bids")
    val bids: List<Bid>,
    @SerializedName("sequence")
    val sequence: Long,
    @SerializedName("updated_at")
    val updatedAt: String,
)
