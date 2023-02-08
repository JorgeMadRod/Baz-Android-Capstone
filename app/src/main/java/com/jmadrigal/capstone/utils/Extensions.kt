package com.jmadrigal.capstone.utils


import java.text.NumberFormat
import java.util.*

fun String.convertToCurrency(): String {
    return "$${NumberFormat.getInstance(Locale("en", "US")).format(this.toDouble())}"
}