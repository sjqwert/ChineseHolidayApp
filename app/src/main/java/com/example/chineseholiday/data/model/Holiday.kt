package com.example.chineseholiday.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 节日类型枚举
 */
enum class HolidayType {
    LEGAL,       // 法定节假日
    TRADITIONAL, // 传统节日
    ETHNIC,      // 民族节日
    INTERNATIONAL // 国际节日
}

/**
 * 日期类型枚举
 */
enum class CalendarType {
    SOLAR,  // 公历
    LUNAR   // 农历
}

/**
 * 节日数据模型
 */
@Entity(tableName = "holidays")
data class Holiday(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,                 // 节日名称
    val month: Int,                   // 月份
    val day: Int,                     // 日期
    val type: HolidayType,            // 节日类型
    val calendarType: CalendarType,   // 日期类型
    val description: String,          // 节日简介
    val history: String,              // 历史由来
    val customs: String,              // 习俗
    val imageUrl: String? = null,     // 节日图片
    val isVacation: Boolean = false   // 是否放假
) 