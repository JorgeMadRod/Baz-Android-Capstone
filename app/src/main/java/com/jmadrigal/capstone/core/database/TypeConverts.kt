package com.jmadrigal.capstone.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jmadrigal.capstone.core.models.Ask
import java.lang.reflect.Type

class TypeConverts {

    @TypeConverter
    fun fromAsk(value: List<Ask>?): String? {
        return if (value == null) {
            null
        } else
            Gson().toJson(value)
    }

    @TypeConverter
    fun toAsk(value: String?): List<Ask>? {
        return if (value == null){
            null
        } else {
            val listType: Type = object : TypeToken<ArrayList<Ask?>?>() {}.getType()
            Gson().fromJson(value, listType)
        }
    }
}