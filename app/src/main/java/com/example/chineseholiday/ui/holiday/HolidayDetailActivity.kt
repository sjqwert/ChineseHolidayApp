package com.example.chineseholiday.ui.holiday

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chineseholiday.R
import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.databinding.ActivityHolidayDetailBinding

class HolidayDetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHolidayDetailBinding
    
    private val viewModel: HolidayDetailViewModel by viewModels {
        HolidayDetailViewModelFactory((application as com.example.chineseholiday.ChineseHolidayApplication).repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolidayDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // 获取节日ID
        val holidayId = intent.getIntExtra(EXTRA_HOLIDAY_ID, -1)
        if (holidayId == -1) {
            finish()
            return
        }
        
        // 加载节日详情
        viewModel.getHolidayById(holidayId)
        
        // 观察节日数据
        viewModel.holiday.observe(this) { holiday ->
            holiday?.let {
                displayHolidayDetails(it)
            }
        }
        
        // 设置添加提醒按钮点击事件
        binding.fabAddReminder.setOnClickListener {
            // 添加节日提醒的功能实现
            Toast.makeText(this, "添加提醒功能待实现", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun displayHolidayDetails(holiday: Holiday) {
        // 设置标题
        binding.collapsingToolbar.title = holiday.name
        
        // 设置节日日期
        val calendarType = if (holiday.calendarType == CalendarType.LUNAR) "农历" else "公历"
        binding.holidayDate.text = "$calendarType${holiday.month}月${holiday.day}日"
        
        // 设置节日简介
        binding.holidayDescription.text = holiday.description
        
        // 设置节日历史
        binding.holidayHistory.text = holiday.history
        
        // 设置节日习俗
        binding.holidayCustoms.text = holiday.customs
        
        // 如果有节日图片，则加载图片
        // 这里简化实现，实际应该使用Glide等图片加载库
        if (holiday.imageUrl != null) {
            // 使用Glide加载图片
            // Glide.with(this).load(holiday.imageUrl).into(binding.holidayImage)
        } else {
            // 使用默认图片
            val colorRes = when (holiday.type) {
                com.example.chineseholiday.data.model.HolidayType.LEGAL -> R.color.holiday_type_legal
                com.example.chineseholiday.data.model.HolidayType.TRADITIONAL -> R.color.holiday_type_traditional
                com.example.chineseholiday.data.model.HolidayType.ETHNIC -> R.color.holiday_type_ethnic
                com.example.chineseholiday.data.model.HolidayType.INTERNATIONAL -> R.color.holiday_type_international
            }
            binding.holidayImage.setBackgroundResource(colorRes)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    
    companion object {
        private const val EXTRA_HOLIDAY_ID = "extra_holiday_id"
        
        fun start(context: Context, holidayId: Int) {
            val intent = Intent(context, HolidayDetailActivity::class.java).apply {
                putExtra(EXTRA_HOLIDAY_ID, holidayId)
            }
            context.startActivity(intent)
        }
    }
} 