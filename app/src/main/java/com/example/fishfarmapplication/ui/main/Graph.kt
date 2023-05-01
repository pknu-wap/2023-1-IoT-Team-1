package com.example.fishfarmapplication.ui.main

import android.graphics.Color
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class Graph(val _chart: LineChart) {
    private val chartData = ArrayList<Entry>()
    private val chart = _chart;

    fun setData(data : List<WaterTemperatureEntity>){
        clearData()
        data.forEach {
            chartData.add(Entry(it.time,it.temperature))
        }

        val lineDataSet = LineDataSet(chartData, "속성명1")
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
    }

    fun getChart() : LineChart{
        return chart;
    }

    fun clearData(){
        chartData.clear()
    }

}