package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MedicineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicineFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding : FragmentMedicineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedicineBinding.inflate(inflater,container,false)
        binding.medicinefloatingbutton.setOnClickListener {
//            HomeCenterStatusDialog().show(childFragmentManager,HomeCenterStatusDialog.TAG)
            MedicineAddTimeDialog().show(childFragmentManager,MedicineAddTimeDialog.TAG)
        }

        return binding.root
    }
}