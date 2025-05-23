package com.example.chineseholiday.ui.holiday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.databinding.ItemHolidayBinding

/**
 * 节日列表适配器
 */
class HolidayAdapter(
    private val onItemClick: (Holiday) -> Unit
) : ListAdapter<Holiday, HolidayAdapter.HolidayViewHolder>(HolidayDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        val binding = ItemHolidayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HolidayViewHolder(binding, onItemClick)
    }
    
    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class HolidayViewHolder(
        private val binding: ItemHolidayBinding,
        private val onItemClick: (Holiday) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(holiday: Holiday) {
            binding.apply {
                tvHolidayName.text = holiday.name
                tvHolidayDate.text = "${holiday.month}月${holiday.day}日"
                tvHolidayType.text = holiday.type.toString()
                tvHolidayDescription.text = holiday.description

                holiday.imageUrl?.let { url ->
                    Glide.with(ivHolidayImage)
                        .load(url)
                        .centerCrop()
                        .into(ivHolidayImage)
                }

                root.setOnClickListener { onItemClick(holiday) }
            }
        }
    }
}

/**
 * 差异化比较工具类
 */
private class HolidayDiffCallback : DiffUtil.ItemCallback<Holiday>() {
    override fun areItemsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
        return oldItem == newItem
    }
} 