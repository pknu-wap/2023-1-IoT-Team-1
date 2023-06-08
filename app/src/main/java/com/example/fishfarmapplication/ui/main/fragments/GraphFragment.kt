package com.example.fishfarmapplication.ui.main.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.String
import kotlin.Float

class GraphFragment : Fragment() {
    private lateinit var binding: FragmentGraphBinding

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef = database.getReference("sensor")
    val entries = ArrayList<Entry>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        val waterChart = binding.waterChart
        val phChart = binding.phChart

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                entries.clear()
                var i = 1
                for (item in snapshot.children) {
                    val test: Float = String.valueOf(item.value).toFloat()
                    entries.add(Entry(i.toFloat(), test))
                    i += 1
                }

                val lineDataSet = LineDataSet(entries, "수온")
                lineDataSet.lineWidth = 2f
                lineDataSet.circleRadius = 6f
                lineDataSet.setDrawCircleHole(true)
                lineDataSet.setDrawCircles(true)

                val xAxis = waterChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.textColor = Color.BLACK
                xAxis.enableGridDashedLine(8F, 24F, 0F)

                val yLAxis = waterChart.axisLeft
                yLAxis.textColor = Color.BLACK

                val yRAxis = waterChart.axisRight
                yRAxis.setDrawLabels(false)
                yRAxis.setDrawAxisLine(false)
                yRAxis.setDrawGridLines(false)

                val lineData = LineData(lineDataSet)
                waterChart.data = lineData

                waterChart.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                entries.clear()
                var i = 1
                for (item in snapshot.children) {
                    val test: Float = String.valueOf(item.value).toFloat()
                    entries.add(Entry(i.toFloat(), test))
                    i += 1
                }

                val lineDataSet = LineDataSet(entries, "pH")
                lineDataSet.lineWidth = 2f
                lineDataSet.circleRadius = 6f
                lineDataSet.setDrawCircleHole(true)
                lineDataSet.setDrawCircles(true)

                val xAxis = phChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.textColor = Color.BLACK
                xAxis.enableGridDashedLine(8F, 24F, 0F)

                val yLAxis = phChart.axisLeft
                yLAxis.textColor = Color.BLACK

                val yRAxis = phChart.axisRight
                yRAxis.setDrawLabels(false)
                yRAxis.setDrawAxisLine(false)
                yRAxis.setDrawGridLines(false)

                val lineData = LineData(lineDataSet)
                phChart.data = lineData

                phChart.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })

        return binding.root
    }
}
//package com.example.fishfarmapplication.ui.main.fragments
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
//import com.example.fishfarmapplication.databinding.FragmentGraphBinding
//import com.example.fishfarmapplication.ui.main.Graph
//import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
//
//class GraphFragment : Fragment() {
//    private val graphDataViewModel: GraphDataViewModel by activityViewModels()
//    private lateinit var binding : FragmentGraphBinding
//    private lateinit var waterChart: Graph
//    private lateinit var phChart : Graph
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentGraphBinding.inflate(inflater, container, false)
//
//        val chartWaterData = graphDataViewModel.allWaterTemperatures.value
//        val phData = graphDataViewModel.allPh.value
//
//        if (chartWaterData != null)
//            waterChart = Graph.of(binding.waterChart, chartWaterData)
//        else
//            waterChart = Graph(binding.waterChart)
//
//        if(phData != null){
//            Log.d("test", "phData : " + phData[0].phData.toString())
//            phChart = Graph.from(binding.phChart, phData)
//        }
//        else{
//            Log.d("graph", "failed")
//            phChart = Graph(binding.phChart)
//        }
//
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        graphDataViewModel.allWaterTemperatures.observe(viewLifecycleOwner, Observer { it->
//            it?.let {
//                waterChart.updateData(it)
//            }
//        })
//
//
//    }
//
//
//
//
//}