package com.example.chineseholiday.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.chineseholiday.R

class SearchFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val textView = root.findViewById<TextView>(R.id.text_search)
        textView.text = "这是搜索页面，功能正在开发中..."
        return root
    }
} 