package com.example.fishfarmapplication.ui.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.databinding.FragmentDialogHomeCenterBinding
import com.example.fishfarmapplication.databinding.FragmentDialogMedicineCenterBinding
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel

class MedicineAddTimeDialog :DialogFragment() {
    private var _binding: FragmentDialogMedicineCenterBinding? = null

    private val homeViewModel : HomeViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

//        _binding = FragmentDialogHomeCenterBinding.inflate(LayoutInflater.from(context))
        _binding = FragmentDialogMedicineCenterBinding.inflate(LayoutInflater.from(context))

        val sumbitBtn = binding.medicineSumbitButton
        sumbitBtn.setOnClickListener{
            dismiss()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val hour = binding.medicineTimePicker.hour.toString()
        val min = binding.medicineTimePicker.minute.toString()

        _binding = null
    }

    companion object{
        const val TAG = "MedicineAddTimeDialog"
    }
}