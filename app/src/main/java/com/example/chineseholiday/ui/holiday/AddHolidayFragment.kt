package com.example.chineseholiday.ui.holiday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chineseholiday.R
import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.model.HolidayType
import com.example.chineseholiday.databinding.FragmentAddHolidayBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddHolidayFragment : Fragment() {

    private var _binding: FragmentAddHolidayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HolidayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHolidayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinners()
        setupSubmitButton()
    }

    private fun setupSpinners() {
        // 设置节日类型下拉列表
        val holidayTypes = HolidayType.values().map { it.name }
        val holidayTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            holidayTypes
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerHolidayType.adapter = holidayTypeAdapter

        // 设置日历类型下拉列表
        val calendarTypes = CalendarType.values().map { it.name }
        val calendarTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            calendarTypes
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerCalendarType.adapter = calendarTypeAdapter
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val month = binding.etMonth.text.toString().toIntOrNull()
            val day = binding.etDay.text.toString().toIntOrNull()
            val description = binding.etDescription.text.toString()
            val history = binding.etHistory.text.toString()
            val customs = binding.etCustoms.text.toString()
            val imageUrl = binding.etImageUrl.text.toString()
            val holidayType = HolidayType.valueOf(
                binding.spinnerHolidayType.selectedItem.toString()
            )
            val calendarType = CalendarType.valueOf(
                binding.spinnerCalendarType.selectedItem.toString()
            )
            val isVacation = binding.switchVacation.isChecked

            if (validateInput(name, month, day)) {
                val holiday = Holiday(
                    name = name,
                    month = month!!,
                    day = day!!,
                    type = holidayType,
                    calendarType = calendarType,
                    description = description,
                    history = history,
                    customs = customs,
                    imageUrl = imageUrl.ifEmpty { null },
                    isVacation = isVacation
                )

                viewModel.addHoliday(holiday)
                findNavController().navigateUp()
            }
        }
    }

    private fun validateInput(name: String, month: Int?, day: Int?): Boolean {
        if (name.isBlank()) {
            showError(getString(R.string.error_empty_name))
            return false
        }

        if (month == null || month !in 1..12) {
            showError(getString(R.string.error_invalid_month))
            return false
        }

        if (day == null || day !in 1..31) {
            showError(getString(R.string.error_invalid_day))
            return false
        }

        return true
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 