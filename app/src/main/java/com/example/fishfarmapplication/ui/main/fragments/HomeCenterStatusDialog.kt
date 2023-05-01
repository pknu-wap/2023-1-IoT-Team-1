package com.example.fishfarmapplication.ui.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fishfarmapplication.databinding.FragmentDialogHomeCenterBinding
import com.example.fishfarmapplication.databinding.FragmentHomeBinding

class HomeCenterStatusDialog : DialogFragment() {

    private var _binding: FragmentDialogHomeCenterBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogHomeCenterBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val TAG = "HomeCenterStatusDialog"
    }

}