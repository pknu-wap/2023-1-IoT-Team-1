package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.MainViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class GraphFragment : Fragment() {
    private lateinit var viewModel:MainViewModel
    private lateinit var binding : FragmentGraphBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container,false)
        val chart = binding.testChart
        val entries = ArrayList<Entry>()
        entries.add(Entry(1.0F, 1.2F))
        entries.add(Entry(7F, 2F))
        entries.add(Entry(1.4F, 1.8F))
        entries.add(Entry(2F, 8F))
        entries.add(Entry(1F, 5F))

        val lineDataSet = LineDataSet(entries, "속성명1")
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 6f

        val lineData = LineData(lineDataSet)
        chart.data = lineData

        chart.invalidate()



        return binding.root
    }
}