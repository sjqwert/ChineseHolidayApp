package com.example.chineseholiday.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chineseholiday.data.model.Holiday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 节日数据库
 */
@Database(entities = [Holiday::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HolidayDatabase : RoomDatabase() {
    
    abstract fun holidayDao(): HolidayDao
    
    companion object {
        @Volatile
        private var INSTANCE: HolidayDatabase? = null
        
        fun getDatabase(context: Context, scope: CoroutineScope): HolidayDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HolidayDatabase::class.java,
                    "holiday_database"
                )
                .addCallback(HolidayDatabaseCallback(scope))
                .build()
                
                INSTANCE = instance
                instance
            }
        }
        
        private class HolidayDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.holidayDao())
                    }
                }
            }
            
            /**
             * 预填充一些节日数据
             */
            suspend fun populateDatabase(holidayDao: HolidayDao) {
                // 清空数据库
                holidayDao.deleteAllHolidays()
                
                // 添加预置节日数据
                val defaultHolidays = HolidayDataInitializer.getDefaultHolidays()
                holidayDao.insertAllHolidays(defaultHolidays)
            }
        }
    }
} 