package com.example.fishfarmapplication.ui.main.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.MainViewModel
import com.github.mikephil.charting.components.XAxis
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
        entries.add(Entry(1F, 1.2F))
        entries.add(Entry(2F, 2F))
        entries.add(Entry(4F, 1.8F))
        entries.add(Entry(5F, 3F))
        entries.add(Entry(6F, 2F))

        val lineDataSet = LineDataSet(entries, "속성명1")
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 6f
        lineDataSet.setDrawCircleHole(true)
        lineDataSet.setDrawCircles(true)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.BLACK
        xAxis.enableGridDashedLine(8F,24F,0F)

        val yLAxis = chart.axisLeft
        yLAxis.textColor = Color.BLACK

        val yRAxis = chart.axisRight
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        val lineData = LineData(lineDataSet)
        chart.data = lineData

        chart.invalidate()



        return binding.root
    }
}