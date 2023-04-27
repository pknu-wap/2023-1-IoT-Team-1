package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fishfarmapplication.databinding.FragmentLedBinding
import com.example.fishfarmapplication.ui.main.MainViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.*


class LedFragment : Fragment() {
    private lateinit var binding : FragmentLedBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = FragmentLedBinding.inflate(inflater, container,false)
        val itemList = ArrayList<LedColorSelectListItem>()
        itemList.add(LedColorSelectListItem("1"))
        itemList.add(LedColorSelectListItem("2"))
        itemList.add(LedColorSelectListItem("3"))

        val itemAdapter = LedColorSelectListAdapter(itemList)
        itemAdapter.notifyDataSetChanged()
        binding.ledColorSelectScroll.adapter = itemAdapter
        return binding.root

    }

}