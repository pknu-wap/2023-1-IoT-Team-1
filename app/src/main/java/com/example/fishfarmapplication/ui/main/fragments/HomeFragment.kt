package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentHomeBinding
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.RecyclerViewHolderHightDeco
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var id: String
    private lateinit var binding : FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var myRef: DatabaseReference
    private val idViewModel: IdViewModel by activityViewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = idViewModel.getValue().toString()
        myRef = database.getReference("users").child(id).child("sensors")
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

        val itemDeco = RecyclerViewHolderHightDeco(30)

        binding.homeRecyclerView.addItemDecoration(itemDeco)
        binding.homeCenterStatusLayout.setOnClickListener {
            HomeCenterStatusDialog().show(childFragmentManager,HomeCenterStatusDialog.TAG)
        }

        binding.btnFeed.setOnClickListener {
            myRef.child("servo").setValue(1)

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                myRef.child("servo").setValue(0)
            }, 10000)
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