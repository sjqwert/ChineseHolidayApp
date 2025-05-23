package com.example.chineseholiday.data

import androidx.room.*
import com.example.chineseholiday.data.model.Holiday
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface HolidayDao {
    @Query("SELECT * FROM holidays ORDER BY date ASC")
    fun getAllHolidays(): Flow<List<Holiday>>

    @Query("SELECT * FROM holidays WHERE date BETWEEN :startDate AND :endDate")
    fun getHolidaysBetweenDates(startDate: Date, endDate: Date): Flow<List<Holiday>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoliday(holiday: Holiday)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHolidays(holidays: List<Holiday>)

    @Delete
    suspend fun deleteHoliday(holiday: Holiday)

    @Query("DELETE FROM holidays")
    suspend fun deleteAllHolidays()
} 