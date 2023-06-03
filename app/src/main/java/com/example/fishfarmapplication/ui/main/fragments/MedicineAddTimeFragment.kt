package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.databinding.FragmentAddMedicineBinding
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.ui.main.alarm.AlarmFunctions
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListItem
import com.example.fishfarmapplication.ui.main.viewmodels.MedicineViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel

class MedicineAddTimeFragment : Fragment() {

    private val medicineViewModel : MedicineViewModel by activityViewModels()
    private val PageViewModel : PageViewModel by activityViewModels()
    private lateinit var binding : FragmentAddMedicineBinding
    private lateinit var alarmFunctions : AlarmFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMedicineBinding.inflate(inflater,container,false)
        alarmFunctions = AlarmFunctions(requireContext())

        binding.addMedicineBackButton.setOnClickListener{
            PageViewModel.updageFragmentStatus(PageType.Medicine)
        }
        binding.addMedicineSumbitBtn.setOnClickListener {
            sumbitAlarm()
            PageViewModel.updageFragmentStatus(PageType.Medicine)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }
    private fun sumbitAlarm(){

        val hour = binding.addMedicineTimePicker.hour
        val min = binding.addMedicineTimePicker.minute

        val year = binding.addMedicineDatePicker.year
        val month = binding.addMedicineDatePicker.month
        val day = binding.addMedicineDatePicker.dayOfMonth


        val time = "$year-$month-$day $hour:$min:00" // 알람이 울리는 시간

        val medicineName = binding.addMedicineItemTitleData.text.toString()
        val medicineDesc = binding.addMedicineItemDescData.text.toString()

        val newMedicineItem = MedicineListItem(time,medicineName,medicineDesc)

        medicineViewModel.addMedicineItemList(newMedicineItem)

        val random = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
        val alarmCode = random.random()
        val content = medicineDesc
        setAlarm(alarmCode, content, time)
    }
}