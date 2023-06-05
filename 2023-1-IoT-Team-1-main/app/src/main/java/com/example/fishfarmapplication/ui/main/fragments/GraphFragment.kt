package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.fishfarmapplication.databinding.FragmentGraphBinding
import com.example.fishfarmapplication.ui.main.Graph
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel

class GraphFragment : Fragment() {
    private val graphDataViewModel: GraphDataViewModel by activityViewModels()
    private lateinit var binding : FragmentGraphBinding
    private lateinit var waterChart: Graph
    private lateinit var phChart : Graph

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container, false)

        val chartWaterData = graphDataViewModel.allWaterTemperatures.value
        val phData = graphDataViewModel.allPh.value

        if (chartWaterData != null)
            waterChart = Graph.of(binding.waterChart, chartWaterData)
        else
            waterChart = Graph(binding.waterChart)

        if(phData != null){
            Log.d("test", "phData : " + phData[0].phData.toString())
            phChart = Graph.from(binding.phChart, phData)
        }
        else{
            Log.d("graph", "failed")
            phChart = Graph(binding.phChart)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphDataViewModel.allWaterTemperatures.observe(viewLifecycleOwner, Observer { it->
            it?.let {
                waterChart.updateData(it)
            }
        })


    }




}