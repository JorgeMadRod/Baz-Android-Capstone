package com.jmadrigal.capstone.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    @SerializedName("success")
    @Expose
    var success: Boolean,
    @SerializedName("payload")
    @Expose
    var payload: OrderBook


)


