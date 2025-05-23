package com.example.chineseholiday.ui.holiday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.repository.HolidayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val repository: HolidayRepository
) : ViewModel() {

    private val _holidays = MutableStateFlow<List<Holiday>>(emptyList())
    val holidays: StateFlow<List<Holiday>> = _holidays

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadHolidays()
    }

    fun loadHolidays() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllHolidays()
                .catch { e ->
                    _error.value = e.message
                    _isLoading.value = false
                }
                .collect { holidays ->
                    _holidays.value = holidays
                    _isLoading.value = false
                }
        }
    }

    fun addHoliday(holiday: Holiday) {
        viewModelScope.launch {
            try {
                repository.insertHoliday(holiday)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteHoliday(holiday: Holiday) {
        viewModelScope.launch {
            try {
                repository.deleteHoliday(holiday)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 