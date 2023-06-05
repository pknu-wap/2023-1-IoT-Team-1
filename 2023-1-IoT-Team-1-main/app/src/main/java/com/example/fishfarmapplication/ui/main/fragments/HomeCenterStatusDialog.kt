package com.example.fishfarmapplication.ui.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.databinding.FragmentDialogHomeCenterBinding
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.IdViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeCenterStatusDialog : DialogFragment() {

    private var _binding: FragmentDialogHomeCenterBinding? = null

    private val homeViewModel : HomeViewModel by activityViewModels()

    private val binding get() = _binding!!

    private lateinit var currentStandardData : currentStandardData

    private lateinit var currentData: currentData

    private lateinit var id: String

    private val idViewModel : IdViewModel by activityViewModels()

    private val database =
        Firebase.database("https://wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
        id = idViewModel.getValue().toString()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogHomeCenterBinding.inflate(LayoutInflater.from(context))

        binding.viewModelXml = homeViewModel

        currentStandardData = currentStandardData(homeViewModel.waterTemperatureStandard.value!!, homeViewModel.phStandard.value!!,
            homeViewModel.foodStandard.value!!)

        currentData = currentData(homeViewModel.waterTemperatureData.value!!, homeViewModel.phData.value!!,homeViewModel.foodData.value!!)


        val sumbitBtn = binding.homeCenterStatusDialogSubmitButton
        sumbitBtn.setOnClickListener{
            dismiss()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val newWaterStandard = binding.homeCenterStatusDialogWaterTemperatureData.text.toString().toFloatOrNull() ?: currentStandardData.waterStandard
        val newPhStandard = binding.homeCenterStatusDialogPHData.text.toString().toFloatOrNull() ?: currentStandardData.phStandard
        val newFoodStandard = binding.homeCenterStatusDialogFoodData.text.toString().toFloatOrNull() ?: currentStandardData.foodStandard

        myRef.child(id).child("temperature").setValue(newWaterStandard)
        myRef.child(id).child("ph").setValue(newPhStandard)
        myRef.child(id).child("feed").setValue(newFoodStandard)

        homeViewModel.setWaterTemperatureStandard(newWaterStandard)
        homeViewModel.setPhStandard(newPhStandard)
        homeViewModel.setFoodStandard(newFoodStandard)
        homeViewModel.checkData()
//        checkStandard(newWaterStandard,newPhStandard,newFoodStandard)
        _binding = null
    }

    companion object{
        const val TAG = "HomeCenterStatusDialog"
    }


}

data class currentStandardData(var waterStandard: Float, var phStandard: Float, var foodStandard: Float)
data class currentData(var waterData: Float, var phData: Float, var foodData: Float)