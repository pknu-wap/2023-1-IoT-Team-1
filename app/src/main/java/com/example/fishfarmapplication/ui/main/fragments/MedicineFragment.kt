package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.RecyclerViewHolderHightDeco
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.MedicineViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel

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
    private val medicineViewModel : MedicineViewModel by activityViewModels()
    private val PageViewModel : PageViewModel by activityViewModels()
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
//            MedicineAddTimeDialog().show(childFragmentManager,MedicineAddTimeDialog.TAG)
//            childFragmentManager.beginTransaction().replace(R., MedicineAddTimeFragment()).commit()
            PageViewModel.updageFragmentStatus(PageType.AddMedicine)
        }

        binding.viewModelXml = medicineViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        val adapter = MedicineListAdapter(medicineViewModel.medicineItemList.value!!)

        adapter.setOnItemClickListener(object :MedicineListAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Log.d("clicked?","cliekd! $position")
                medicineViewModel.setclickedMedicineItem(position)
                PageViewModel.updageFragmentStatus(PageType.EditMedicine)
            }
        })

        binding.adapter = adapter

        val itemDeco = RecyclerViewHolderHightDeco(30)
        binding.medicineRecyclerView.addItemDecoration(itemDeco)

        return binding.root
    }

    fun showEditFragment(){
        PageViewModel.updageFragmentStatus(PageType.EditMedicine)
    }
}