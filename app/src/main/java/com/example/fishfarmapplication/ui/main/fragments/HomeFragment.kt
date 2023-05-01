package com.example.fishfarmapplication.ui.main.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fishfarmapplication.databinding.FragmentHomeBinding
import com.example.fishfarmapplication.ui.main.models.entity.WaterTemperatureEntity
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListDeco
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListItem
import com.example.fishfarmapplication.ui.main.viewmodels.DataViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: PageViewModel
    private val dataViewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PageViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        val itemList = ArrayList<HomeListItem>()
        itemList.add(HomeListItem("수온","테스트"))
        itemList.add(HomeListItem("PH","테스트"))
        itemList.add(HomeListItem("먹이","테스트"))

        val itemAdapter = HomeListAdapter(itemList)
        val itemDeco = HomeListDeco(30)
        itemAdapter.notifyDataSetChanged()
        binding.homeRecyclerView.adapter = itemAdapter
        binding.homeRecyclerView.addItemDecoration(itemDeco)

        binding.homeCenterStatusLayout.setOnClickListener {
            HomeCenterStatusDialog().show(childFragmentManager,HomeCenterStatusDialog.TAG)
        }

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