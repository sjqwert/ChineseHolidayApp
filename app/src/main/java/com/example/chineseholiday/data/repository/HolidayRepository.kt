package com.example.chineseholiday.data.repository

import androidx.lifecycle.LiveData
import com.example.chineseholiday.data.db.HolidayDao
import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.model.HolidayType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 节日仓库类，用于处理节日数据
 */
@Singleton
class HolidayRepository @Inject constructor(
    private val holidayDao: HolidayDao
) {
    
    // 获取所有节日
    fun getAllHolidays(): Flow<List<Holiday>> = holidayDao.getAllHolidays()
    
    // 根据类型获取节日
    fun getHolidaysByType(type: HolidayType): LiveData<List<Holiday>> {
        return holidayDao.getHolidaysByType(type)
    }
    
    // 根据月份获取节日
    fun getHolidaysByMonth(month: Int): Flow<List<Holiday>> = holidayDao.getHolidaysByMonth(month)
    
    // 根据日期获取节日
    fun getHolidaysByDate(month: Int, day: Int): LiveData<List<Holiday>> {
        return holidayDao.getHolidaysByDate(month, day)
    }
    
    // 根据日期和日历类型获取节日
    fun getHolidaysByDateAndCalendarType(month: Int, day: Int, calendarType: CalendarType): LiveData<List<Holiday>> {
        return holidayDao.getHolidaysByDateAndCalendarType(month, day, calendarType)
    }
    
    // 根据ID获取节日
    fun getHolidayById(id: Int): LiveData<Holiday> {
        return holidayDao.getHolidayById(id)
    }
    
    // 搜索节日
    fun searchHolidays(query: String): LiveData<List<Holiday>> {
        return holidayDao.searchHolidays(query)
    }
    
    // 插入节日
    suspend fun insertHoliday(holiday: Holiday) = holidayDao.insertHoliday(holiday)
    
    // 批量插入节日
    suspend fun insertHolidays(holidays: List<Holiday>) = holidayDao.insertHolidays(holidays)
    
    // 更新节日
    suspend fun update(holiday: Holiday) {
        holidayDao.updateHoliday(holiday)
    }
    
    // 删除节日
    suspend fun deleteHoliday(holiday: Holiday) = holidayDao.deleteHoliday(holiday)
    
    // 删除所有节日
    suspend fun deleteAllHolidays() = holidayDao.deleteAllHolidays()
}