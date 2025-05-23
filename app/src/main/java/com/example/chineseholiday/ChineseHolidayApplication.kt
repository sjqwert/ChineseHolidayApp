package com.example.chineseholiday

import android.app.Application
import com.example.chineseholiday.data.db.HolidayDatabase
import com.example.chineseholiday.data.repository.HolidayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ChineseHolidayApplication : Application() {
    
    // 使用ApplicationScope来处理应用级别的协程
    private val applicationScope = CoroutineScope(SupervisorJob())
    
    // 懒加载数据库
    private val database by lazy {
        HolidayDatabase.getDatabase(this, applicationScope)
    }
    
    // 懒加载仓库
    val repository by lazy {
        HolidayRepository(database.holidayDao())
    }
    
    override fun onCreate() {
        super.onCreate()
        // 可以在这里进行其他初始化操作
    }
} 
 