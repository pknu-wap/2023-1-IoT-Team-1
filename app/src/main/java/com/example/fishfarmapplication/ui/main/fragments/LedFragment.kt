package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentLedBinding
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.*


class LedFragment : Fragment() {
    private lateinit var binding : FragmentLedBinding
    private lateinit var viewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PageViewModel::class.java)
        binding = FragmentLedBinding.inflate(inflater, container,false)
        val itemList = ArrayList<LedColorSelectListItem>()
        itemList.add(LedColorSelectListItem(R.color.led_select_green))
        itemList.add(LedColorSelectListItem(R.color.led_select_blue))
        itemList.add(LedColorSelectListItem(R.color.led_select_red))

        val itemAdapter = LedColorSelectListAdapter(itemList)
        itemAdapter.notifyDataSetChanged()
        binding.ledColorSelectScroll.adapter = itemAdapter
        return binding.root

    }

}