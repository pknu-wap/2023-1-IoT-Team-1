package com.example.fishfarmapplication.ui.main.fragments

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.PostProcessor
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.MainViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.String
import kotlin.Float
import kotlin.Int

class GraphFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
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
        val chart = binding.testChart

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                entries.clear()
                var i = 1
                for (item in snapshot.children) {
                    val test: Float = String.valueOf(item.value).toFloat()
                    entries.add(Entry(i.toFloat(), test))
                    i += 1
                }

                val lineDataSet = LineDataSet(entries, "속성명1")
                lineDataSet.lineWidth = 2f
                lineDataSet.circleRadius = 6f
                lineDataSet.setDrawCircleHole(true)
                lineDataSet.setDrawCircles(true)

                val xAxis = chart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.textColor = Color.BLACK
                xAxis.enableGridDashedLine(8F, 24F, 0F)

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

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })

        return binding.root
    }
}