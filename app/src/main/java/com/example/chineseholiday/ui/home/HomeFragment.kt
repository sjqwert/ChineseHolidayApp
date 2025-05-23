package com.example.chineseholiday.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chineseholiday.R
import com.example.chineseholiday.databinding.FragmentHomeBinding
import com.example.chineseholiday.data.model.HolidayType
import com.example.chineseholiday.ui.holiday.HolidayAdapter
import java.util.*

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((requireActivity().application as com.example.chineseholiday.ChineseHolidayApplication).repository)
    }
    
    private lateinit var adapter: HolidayAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupCategoryCards()
        
        // 获取本月节日
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH是从0开始的
        viewModel.getHolidaysByMonth(currentMonth)
        
        // 观察下一个节日数据
        viewModel.nextHoliday.observe(viewLifecycleOwner) { holiday ->
            holiday?.let {
                binding.nextHolidayName.text = it.name
                binding.nextHolidayDesc.text = it.description
                
                // 计算距离节日还有多少天
                // 简化实现，实际应考虑农历日期转换
                val holidayCalendar = Calendar.getInstance()
                holidayCalendar.set(Calendar.MONTH, it.month - 1)
                holidayCalendar.set(Calendar.DAY_OF_MONTH, it.day)
                
                val today = Calendar.getInstance()
                
                // 如果今年的节日已经过了，则计算到明年的日期
                if (holidayCalendar.before(today)) {
                    holidayCalendar.add(Calendar.YEAR, 1)
                }
                
                val daysLeft = ((holidayCalendar.timeInMillis - today.timeInMillis) / (24 * 60 * 60 * 1000)).toInt()
                binding.nextHolidayDate.text = "距离还有 $daysLeft 天"
            }
        }
        
        // 观察本月节日数据
        viewModel.currentMonthHolidays.observe(viewLifecycleOwner) { holidays ->
            adapter.submitList(holidays)
        }
    }
    
    private fun setupRecyclerView() {
        adapter = HolidayAdapter { holiday ->
            // 点击节日项时导航到详情页面
            val action = HomeFragmentDirections.actionNavigationHomeToHolidayDetail(holiday.id)
            findNavController().navigate(action)
        }
        
        binding.currentMonthHolidays.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }
    
    private fun setupCategoryCards() {
        // 法定节假日分类点击事件
        binding.cardLegalHoliday.setOnClickListener {
            navigateToHolidayList(HolidayType.LEGAL)
        }
        
        // 传统节日分类点击事件
        binding.cardTraditionalHoliday.setOnClickListener {
            navigateToHolidayList(HolidayType.TRADITIONAL)
        }
        
        // 民族节日分类点击事件
        binding.cardEthnicHoliday.setOnClickListener {
            navigateToHolidayList(HolidayType.ETHNIC)
        }
    }
    
    private fun navigateToHolidayList(type: HolidayType) {
        // 导航到节日列表页面，并传递节日类型
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationHolidayList(type.name)
        findNavController().navigate(action)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 