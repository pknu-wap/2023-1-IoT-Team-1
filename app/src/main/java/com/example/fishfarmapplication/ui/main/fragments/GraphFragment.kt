package com.example.fishfarmapplication.ui.main.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.Graph
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import com.example.fishfarmapplication.ui.main.viewmodels.DataViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphFragment : Fragment() {
    private val dataViewModel: DataViewModel by viewModels()
    private lateinit var binding : FragmentGraphBinding
    private lateinit var chart: Graph

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container,false)
        chart = Graph(binding.testChart)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel.allWaterTemperatures.observe(viewLifecycleOwner, Observer {it->
            it?.let {
                chart.setData(it)
            }
        })


    }

    private fun initDatabase(){
        lifecycleScope.launch ( Dispatchers.IO  ){
//            dataViewModel.deleteAll()
            dataViewModel.insert(WaterTemperatureEntity(1F,2F))
            dataViewModel.insert(WaterTemperatureEntity(1.3F,4F))
            dataViewModel.insert(WaterTemperatureEntity(1.6F,5F))
        }
    }



}