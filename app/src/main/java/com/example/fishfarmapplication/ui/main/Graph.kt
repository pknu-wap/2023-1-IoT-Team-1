package com.example.fishfarmapplication.ui.main

import android.graphics.Color
import android.util.Log
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class Graph(val _chart: LineChart) {
    private val chartData = ArrayList<Entry>()
    private val lineChart = _chart;
    init {
        //차트의 범례 설정
        val legend = lineChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.form = Legend.LegendForm.CIRCLE
        legend.formSize = 10f
        legend.textSize = 13f
        legend.textColor = Color.parseColor("#A3A3A3")
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.yEntrySpace = 5f
        legend.isWordWrapEnabled = true
        legend.xOffset = 80f
        legend.yOffset = 20f
        legend.calculatedLineSizes

        //XAxis 설정 (아래쪽)
        val xAxis = lineChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.granularity = 1f
        xAxis.textSize = 14f
        xAxis.textColor = Color.rgb(118, 118, 118)
        xAxis.spaceMin = 0.1f
        xAxis.spaceMax = 0.1f

        xAxis.valueFormatter = TimeAxisValueFormat()



        // YAxis(Right) (왼쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
        val yAxisLeft = lineChart.axisLeft
        yAxisLeft.textSize = 14f
        yAxisLeft.textColor = Color.rgb(163, 163, 163)
        yAxisLeft.setDrawAxisLine(false)
        yAxisLeft.axisLineWidth = 2f
        yAxisLeft.axisMinimum = 0f // 최솟값

        // YAxis(Left) (오른쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
        val yAxis = lineChart.axisRight
        yAxis.setDrawLabels(false) // label 삭제

        yAxis.textColor = Color.rgb(163, 163, 163)
        yAxis.setDrawAxisLine(false)
        yAxis.axisLineWidth = 2f
        yAxis.axisMinimum = 0f // 최솟값

    }

    fun setData(data : List<WaterTemperatureEntity>){
        clearData()
        data.forEach {
            chartData.add(Entry(it.time,it.temperature))
        }

        val lineDataSet = LineDataSet(chartData, "수온")
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 6f
        lineDataSet.setDrawCircleHole(true)
        lineDataSet.setDrawCircles(true)





        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.BLACK
        xAxis.enableGridDashedLine(8F,24F,0F)

        val yLAxis = lineChart.axisLeft
        yLAxis.textColor = Color.BLACK

        val yRAxis = lineChart.axisRight
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        val lineData = LineData(lineDataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    fun getChart() : LineChart{
        return lineChart;
    }

    fun clearData(){
        chartData.clear()
    }



}

class TimeAxisValueFormat: IndexAxisValueFormatter(){
    override fun getFormattedValue(value: Float): String {
        var valueToMinutes = TimeUnit.DAYS.toDays(value.toLong())
        var date = Date(valueToMinutes)
        var formatDate = SimpleDateFormat("MM:dd")
        return formatDate.format(date)
    }
}