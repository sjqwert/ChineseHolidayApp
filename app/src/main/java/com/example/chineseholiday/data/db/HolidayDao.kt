package com.example.chineseholiday.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.model.HolidayType

/**
 * 节日数据库访问接口
 */
@Dao
interface HolidayDao {
    
    @Query("SELECT * FROM holidays ORDER BY month, day")
    fun getAllHolidays(): LiveData<List<Holiday>>
    
    @Query("SELECT * FROM holidays WHERE type = :type ORDER BY month, day")
    fun getHolidaysByType(type: HolidayType): LiveData<List<Holiday>>
    
    @Query("SELECT * FROM holidays WHERE month = :month ORDER BY day")
    fun getHolidaysByMonth(month: Int): LiveData<List<Holiday>>
    
    @Query("SELECT * FROM holidays WHERE month = :month AND day = :day")
    fun getHolidaysByDate(month: Int, day: Int): LiveData<List<Holiday>>
    
    @Query("SELECT * FROM holidays WHERE month = :month AND day = :day AND calendarType = :calendarType")
    fun getHolidaysByDateAndCalendarType(month: Int, day: Int, calendarType: CalendarType): LiveData<List<Holiday>>
    
    @Query("SELECT * FROM holidays WHERE id = :id")
    fun getHolidayById(id: Int): LiveData<Holiday>
    
    @Query("SELECT * FROM holidays WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR customs LIKE '%' || :query || '%' OR history LIKE '%' || :query || '%'")
    fun searchHolidays(query: String): LiveData<List<Holiday>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoliday(holiday: Holiday)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHolidays(holidays: List<Holiday>)
    
    @Update
    suspend fun updateHoliday(holiday: Holiday)
    
    @Delete
    suspend fun deleteHoliday(holiday: Holiday)
    
    @Query("DELETE FROM holidays")
    suspend fun deleteAllHolidays()
} 