package com.example.fishfarmapplication.ui.main

import android.graphics.Color
import com.example.fishfarmapplication.ui.main.models.entity.GraphEntity
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureTuple
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class Graph(val _chart: LineChart) {
    private var chartData = ArrayList<Entry>()
    private var lineChart = _chart;


    constructor(_chart: LineChart, _chartData: List<WaterTemperatureTuple>) : this(_chart) {
        this.lineChart = _chart
        initGraph(_chartData)
    }

    init {
        decoGraph()
    }

    fun initGraph(_chartData: List<WaterTemperatureTuple>){
        decoGraph()
        initData(_chartData)
    }

    fun decoGraph() {
        lineChart.apply {
            axisRight.isEnabled = false
            axisLeft.axisMaximum = 10f
            legend.apply {
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 10f
                setDrawGridLines(false)
                granularity = 1f
//            axisMinimum = 2f
                isGranularityEnabled = true

                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val time = value.toInt()
                        return "${(time / 10000).toString().padStart(2, '0')}." +
                                "${((time % 10000) / 100).toString().padStart(2, '0')}." +
                                "${(time % 100).toString().padStart(2, '0')}"
                    }
                }

            }

//            isDoubleTapToZoomEnabled = false
//            setPinchZoom(false)
//            description.text = "시간 별 수온"
//            description.textSize = 15f

        }


        //차트의 범례 설정
//        val legend = lineChart.legend
//        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.formSize = 10f
//        legend.textSize = 13f
//        legend.textColor = Color.parseColor("#A3A3A3")
//        legend.orientation = Legend.LegendOrientation.VERTICAL
//        legend.setDrawInside(false)
//        legend.yEntrySpace = 5f
//        legend.isWordWrapEnabled = true
//        legend.xOffset = 80f
//        legend.yOffset = 20f
//        legend.calculatedLineSizes

    }

    fun initData(data: List<WaterTemperatureTuple>) {
        data.forEach {
            chartData.add(Entry(it.time, it.temperature))
        }

        val dataSet = LineDataSet(chartData, "수온")
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 6f
        dataSet.setDrawCircleHole(true)
        dataSet.setDrawCircles(true)

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    fun updateData(data: List<WaterTemperatureTuple>) {
        clearData()
        data.forEach {
            chartData.add(Entry(it.time, it.temperature))
        }
        val dataSet = LineDataSet(chartData, "수온")
        lineChart.data = LineData(dataSet)
        lineChart.data.notifyDataChanged()
        lineChart.invalidate()
    }

    fun getChart(): LineChart {
        return lineChart;
    }

    fun clearData() {
        chartData.clear()
    }


}