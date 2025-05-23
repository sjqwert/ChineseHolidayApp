package com.example.chineseholiday.ui.holiday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.repository.HolidayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayDetailViewModel @Inject constructor(
    private val repository: HolidayRepository
) : ViewModel() {

    private val _holiday = MutableLiveData<Holiday>()
    val holiday: LiveData<Holiday> = _holiday

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadHoliday(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getHolidayById(id).collect { holiday ->
                    _holiday.value = holiday
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }
}

/**
 * ViewModel工厂
 */
class HolidayDetailViewModelFactory(private val repository: HolidayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HolidayDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HolidayDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 