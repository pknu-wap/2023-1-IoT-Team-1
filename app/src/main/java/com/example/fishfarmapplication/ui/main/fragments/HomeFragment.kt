package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel

class HomeFragment : Fragment() {

    private lateinit var id: String
    private lateinit var binding : FragmentHomeBinding
    private val viewModel: PageViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var itemAdapter : HomeListAdapter
    private val idViewModel: IdViewModel by activityViewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = idViewModel.getValue().toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.viewModelXml = homeViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        homeViewModel.checkData()

//        val recentcurrentData = currentData(homeViewModel.waterTemperatureData.value!!,homeViewModel.phData.value!!,
//        homeViewModel.foodData.value!!)
//
//        itemList.add(HomeListItem("수온",recentcurrentData.waterData.toString(), homeViewModel.waterTemperatureStatus.value!!))
//        itemList.add(HomeListItem("PH",recentcurrentData.phData.toString(), homeViewModel.phStatus.value!!))
//        itemList.add(HomeListItem("먹이",recentcurrentData.foodData.toString(), homeViewModel.foodStatus.value!!))


        val itemDeco = HomeListDeco(30)

        binding.homeRecyclerView.addItemDecoration(itemDeco)
        binding.homeCenterStatusLayout.setOnClickListener {
            HomeCenterStatusDialog().show(childFragmentManager,HomeCenterStatusDialog.TAG)
        }

//        binding.homeCenterStatusLayout.setOnClickListener {
//            homeViewModel.setPhData(homeViewModel.phData.value!! + 1)
//        }
//
        homeViewModel.phData.observe(viewLifecycleOwner, Observer {
            homeViewModel.checkData()
        })
        homeViewModel.waterTemperatureData.observe(viewLifecycleOwner, Observer {
            homeViewModel.checkData()
        })
        homeViewModel.foodData.observe(viewLifecycleOwner, Observer {
            homeViewModel.checkData()

        })

        return binding.root
    }
}