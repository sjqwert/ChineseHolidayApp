package com.example.chineseholiday.ui.holiday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.chineseholiday.databinding.FragmentHolidayDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HolidayDetailFragment : Fragment() {

    private var _binding: FragmentHolidayDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HolidayDetailViewModel by viewModels()
    private val args: HolidayDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHolidayDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadHoliday(args.holidayId)

        viewModel.holiday.observe(viewLifecycleOwner) { holiday ->
            holiday?.let {
                binding.apply {
                    tvHolidayName.text = it.name
                    tvHolidayDate.text = "${it.month}月${it.day}日"
                    tvHolidayType.text = it.type.toString()
                    tvDescription.text = it.description
                    tvHistory.text = it.history
                    tvCustoms.text = it.customs

                    it.imageUrl?.let { url ->
                        Glide.with(ivHolidayImage)
                            .load(url)
                            .centerCrop()
                            .into(ivHolidayImage)
                    }

                    // 设置是否放假标记
                    chipVacation.isChecked = it.isVacation
                    chipVacation.isClickable = false
                }
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                // 显示错误信息
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 