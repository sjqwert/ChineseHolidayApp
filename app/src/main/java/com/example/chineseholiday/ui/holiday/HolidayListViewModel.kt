package com.example.chineseholiday.ui.holiday

import androidx.lifecycle.*
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.model.HolidayType
import com.example.chineseholiday.data.repository.HolidayRepository

class HolidayListViewModel(private val repository: HolidayRepository) : ViewModel() {
    
    // 节日列表数据
    private val _holidays = MutableLiveData<List<Holiday>>()
    val holidays: LiveData<List<Holiday>> = _holidays
    
    // 当前选中的节日类型
    private val _currentType = MutableLiveData<HolidayType?>()
    
    init {
        // 初始化时获取所有节日
        getAllHolidays()
    }
    
    /**
     * 获取所有节日
     */
    fun getAllHolidays() {
        _currentType.value = null
        repository.allHolidays.observeForever { holidays ->
            _holidays.value = holidays
        }
    }
    
    /**
     * 根据类型获取节日
     */
    fun getHolidaysByType(type: HolidayType) {
        if (_currentType.value == type) {
            return // 避免重复加载
        }
        
        _currentType.value = type
        repository.getHolidaysByType(type).observeForever { holidays ->
            _holidays.value = holidays
        }
    }
}

/**
 * ViewModel工厂
 */
class HolidayListViewModelFactory(private val repository: HolidayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HolidayListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HolidayListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 