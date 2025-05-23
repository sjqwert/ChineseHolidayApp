package com.example.chineseholiday.ui.holiday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chineseholiday.data.model.HolidayType
import com.example.chineseholiday.databinding.FragmentHolidayListBinding
import com.google.android.material.tabs.TabLayout

class HolidayListFragment : Fragment() {
    
    private var _binding: FragmentHolidayListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HolidayListViewModel by viewModels {
        HolidayListViewModelFactory((requireActivity().application as com.example.chineseholiday.ChineseHolidayApplication).repository)
    }
    
    private lateinit var adapter: HolidayAdapter
    
    private val args: HolidayListFragmentArgs by navArgs()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHolidayListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupTabLayout()
        
        // 获取从导航传递过来的节日类型
        val holidayTypeStr = args.holidayType
        if (!holidayTypeStr.isNullOrEmpty()) {
            val holidayType = HolidayType.valueOf(holidayTypeStr)
            // 选择对应的Tab
            when (holidayType) {
                HolidayType.LEGAL -> binding.tabLayout.getTabAt(0)?.select()
                HolidayType.TRADITIONAL -> binding.tabLayout.getTabAt(1)?.select()
                HolidayType.ETHNIC -> binding.tabLayout.getTabAt(2)?.select()
                HolidayType.INTERNATIONAL -> binding.tabLayout.getTabAt(3)?.select()
            }
            // 加载对应类型的节日
            viewModel.getHolidaysByType(holidayType)
        } else {
            // 默认加载所有节日
            viewModel.getAllHolidays()
        }
        
        // 观察节日数据
        viewModel.holidays.observe(viewLifecycleOwner) { holidays ->
            binding.progressBar.visibility = View.GONE
            
            if (holidays.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.holidayList.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.GONE
                binding.holidayList.visibility = View.VISIBLE
                adapter.submitList(holidays)
            }
        }
    }
    
    private fun setupRecyclerView() {
        adapter = HolidayAdapter { holiday ->
            // 点击节日项时导航到详情页面
            HolidayDetailActivity.start(requireContext(), holiday.id)
        }
        
        binding.holidayList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HolidayListFragment.adapter
        }
    }
    
    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.progressBar.visibility = View.VISIBLE
                    
                    // 根据选中的Tab加载不同类型的节日
                    when (it.position) {
                        0 -> viewModel.getHolidaysByType(HolidayType.LEGAL)
                        1 -> viewModel.getHolidaysByType(HolidayType.TRADITIONAL)
                        2 -> viewModel.getHolidaysByType(HolidayType.ETHNIC)
                        3 -> viewModel.getHolidaysByType(HolidayType.INTERNATIONAL)
                    }
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 不需要处理
            }
            
            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 不需要处理
            }
        })
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 