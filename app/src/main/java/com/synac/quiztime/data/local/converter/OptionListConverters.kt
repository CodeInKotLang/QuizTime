package com.synac.quiztime.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OptionListConverters {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromStringToList(listString: String): List<String> {
        return Json.decodeFromString(listString)
    }

}