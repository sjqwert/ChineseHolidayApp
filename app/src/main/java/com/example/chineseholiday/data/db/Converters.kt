package com.example.chineseholiday.data.db

import androidx.room.TypeConverter
import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.HolidayType

/**
 * Room数据库类型转换器
 */
class Converters {
    
    @TypeConverter
    fun fromHolidayType(type: HolidayType): String {
        return type.name
    }
    
    @TypeConverter
    fun toHolidayType(value: String): HolidayType {
        return HolidayType.valueOf(value)
    }
    
    @TypeConverter
    fun fromCalendarType(type: CalendarType): String {
        return type.name
    }
    
    @TypeConverter
    fun toCalendarType(value: String): CalendarType {
        return CalendarType.valueOf(value)
    }
} 