package com.example.chineseholiday.ui.home

import androidx.lifecycle.*
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.repository.HolidayRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val repository: HolidayRepository) : ViewModel() {
    
    // 所有节日
    val allHolidays: LiveData<List<Holiday>> = repository.allHolidays
    
    // 当前月份的节日
    private val _currentMonthHolidays = MutableLiveData<List<Holiday>>()
    val currentMonthHolidays: LiveData<List<Holiday>> = _currentMonthHolidays
    
    // 下一个节日
    private val _nextHoliday = MutableLiveData<Holiday?>()
    val nextHoliday: LiveData<Holiday?> = _nextHoliday
    
    init {
        // 初始化时查找下一个节日
        findNextHoliday()
    }
    
    /**
     * 按月份获取节日
     */
    fun getHolidaysByMonth(month: Int) {
        repository.getHolidaysByMonth(month).observeForever { holidays ->
            _currentMonthHolidays.value = holidays
        }
    }
    
    /**
     * 查找下一个节日
     */
    private fun findNextHoliday() {
        allHolidays.observeForever { holidays ->
            if (holidays.isNotEmpty()) {
                val calendar = Calendar.getInstance()
                val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH是从0开始的
                val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
                
                // 找出今天之后的第一个节日
                // 注意：这里的实现比较简化，实际还需要处理农历转公历的问题
                var nextHoliday = holidays.find { 
                    (it.month > currentMonth) || (it.month == currentMonth && it.day >= currentDay)
                }
                
                // 如果当前年份没有找到下一个节日，就取下一年的第一个节日
                if (nextHoliday == null && holidays.isNotEmpty()) {
                    nextHoliday = holidays.minByOrNull { it.month * 100 + it.day }
                }
                
                _nextHoliday.value = nextHoliday
            }
        }
    }
}

/**
 * ViewModel工厂
 */
class HomeViewModelFactory(private val repository: HolidayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 