package com.jmadrigal.capstone.utils


import java.text.NumberFormat
import java.util.*

object Extensions {

    fun String.convertToCurrency(): String {
        return "$${NumberFormat.getInstance(Locale("en", "US")).format(this.toDouble())}"
    }
}