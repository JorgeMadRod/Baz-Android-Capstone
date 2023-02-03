package com.jmadrigal.capstone.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderBook(
    @SerializedName("updated_at")
    @Expose
    var updated_at: String,
    @SerializedName("sequence")
    @Expose
    var sequence: String,
    @SerializedName("bids")
    @Expose
    var bids: List<Ask>,
    @SerializedName("asks")
    @Expose
    var asks: List<Ask>



)