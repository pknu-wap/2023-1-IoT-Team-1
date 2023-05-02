package com.example.fishfarmapplication.ui.main.fragments

<<<<<<< HEAD
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.PostProcessor
=======
>>>>>>> UI
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
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
=======
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.Graph
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphFragment : Fragment() {
    private val graphDataViewModel: GraphDataViewModel by activityViewModels()
    private lateinit var binding : FragmentGraphBinding
    private lateinit var chart: Graph
>>>>>>> UI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
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
=======
        binding = FragmentGraphBinding.inflate(inflater, container,false)
>>>>>>> UI

        val charData = graphDataViewModel.getAllWaterTemperature()
        if (charData != null)
            chart = Graph(binding.testChart,charData)
        else
            chart = Graph(binding.testChart)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphDataViewModel.allWaterTemperatures.observe(viewLifecycleOwner, Observer { it->
            it?.let {
                chart.updateData(it)
            }
        })


    }




}