package com.jmadrigal.capstone.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    @SerializedName("success")
    @Expose
    var success: Boolean,
    @SerializedName("payload")
    @Expose
    var payload: ArrayList<Book>
)
