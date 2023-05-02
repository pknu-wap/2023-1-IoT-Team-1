package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
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
    private lateinit var chart: Graph

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        val charData = graphDataViewModel.getAllWaterTemperature()
        if (charData != null)
            chart = Graph(binding.testChart, charData)
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