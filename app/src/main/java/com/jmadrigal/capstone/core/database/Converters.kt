package com.jmadrigal.capstone.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jmadrigal.capstone.core.models.Ask
import java.lang.reflect.Type


open class Converters {
    @TypeConverter
    fun asksFromString(value: String?): List<Ask> {
        val listType: Type = object : TypeToken<ArrayList<Ask?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun asksFromArrayList(list: List<Ask?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}