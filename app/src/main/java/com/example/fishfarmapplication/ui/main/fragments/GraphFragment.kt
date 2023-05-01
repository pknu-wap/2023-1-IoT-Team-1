package com.example.fishfarmapplication.ui.main.fragments

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
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphFragment : Fragment() {
    private val graphDataViewModel: GraphDataViewModel by viewModels()
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
        initDatabase()

        graphDataViewModel.allWaterTemperatures.observe(viewLifecycleOwner, Observer { it->
            it?.let {
                chart.setData(it)
            }
        })


    }

    private fun initDatabase(){
        lifecycleScope.launch ( Dispatchers.IO  ){


        }
    }



}