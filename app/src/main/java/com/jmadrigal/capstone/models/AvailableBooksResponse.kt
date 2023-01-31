package com.jmadrigal.capstone.models

import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("payload")
    var payload: List<Book>
)
