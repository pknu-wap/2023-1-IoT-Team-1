package com.example.fishfarmapplication.ui.main.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentHomeBinding
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListDeco
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListItem
import com.example.fishfarmapplication.ui.main.viewmodels.GraphDataViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel: PageViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        val itemList = ArrayList<HomeListItem>()
        val waterData = homeViewModel.waterTemperatureData
        val phData = homeViewModel.phData

        val recentcurrentData = currentData(homeViewModel.waterTemperatureData.value!!,homeViewModel.phData.value!!,
        homeViewModel.foodData.value!!)

        itemList.add(HomeListItem("수온",recentcurrentData.waterData.toString()))
        itemList.add(HomeListItem("PH",recentcurrentData.phData.toString()))
        itemList.add(HomeListItem("먹이",recentcurrentData.foodData.toString()))

        val itemAdapter = HomeListAdapter(itemList)
        val itemDeco = HomeListDeco(30)
        itemAdapter.notifyDataSetChanged()
        binding.homeRecyclerView.adapter = itemAdapter
        binding.homeRecyclerView.addItemDecoration(itemDeco)
        binding.homeCenterStatusLayout.setOnClickListener {
            HomeCenterStatusDialog().show(childFragmentManager,HomeCenterStatusDialog.TAG)
        }

        homeViewModel.homeStatus.observe(viewLifecycleOwner, Observer {
            binding.invalidateAll()
        })

        binding.viewModelXml= homeViewModel

        return binding.root
    }

    companion object{
        fun newInstance(title:String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }







}