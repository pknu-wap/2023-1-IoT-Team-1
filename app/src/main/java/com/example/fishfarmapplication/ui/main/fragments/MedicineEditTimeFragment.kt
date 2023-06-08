package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.databinding.FragmentAddMedicineBinding
import com.example.fishfarmapplication.databinding.FragmentEditMedicineBinding
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.ui.main.alarm.AlarmFunctions
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListItem
import com.example.fishfarmapplication.ui.main.viewmodels.MedicineViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.time.Duration.Companion.hours

class MedicineEditTimeFragment : Fragment(), DatePicker.OnDateChangedListener {

    private val medicineViewModel : MedicineViewModel by activityViewModels()
    private val PageViewModel : PageViewModel by activityViewModels()
    private lateinit var binding : FragmentEditMedicineBinding
    private lateinit var alarmFunctions : AlarmFunctions

    companion object{
        val yearStringFormatter = SimpleDateFormat("yyyy년MM월dd일")
        val hourStringFormatter = SimpleDateFormat("HH:MM")
        val timeStringFormatter = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        val aaStringFormat = SimpleDateFormat("aa")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditMedicineBinding.inflate(inflater,container,false)
        alarmFunctions = AlarmFunctions(requireContext())
        initViews(medicineViewModel.clickedMedicineItem.value!!)

        binding.addMedicineBackButton.setOnClickListener{
            PageViewModel.updageFragmentStatus(PageType.Medicine)
        }
        binding.addMedicineSumbitBtn.setOnClickListener {
            submitAlarm()
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

    private fun initViews(position: Int){
        val time = timeStringFormatter.parse(medicineViewModel.medicineItemList.value!!.get(position).time)
        val calendar = Calendar.getInstance()
        calendar.time = time
        Log.d("datepicker : ", calendar.get(Calendar.YEAR).toString())

        binding.addMedicineItemTitleData.setText(medicineViewModel.medicineItemList.value!!.get(position).name)
        binding.addMedicineItemDescData.setText(medicineViewModel.medicineItemList.value!!.get(position).desc)
        binding.addMedicineTimePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
        binding.addMedicineTimePicker.minute = calendar.get(Calendar.MINUTE)
        binding.addMedicineDatePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), null)

    }

    private fun submitAlarm(){
        val hour = binding.addMedicineTimePicker.hour
        val min = binding.addMedicineTimePicker.minute

        val year = binding.addMedicineDatePicker.year
        val month = binding.addMedicineDatePicker.month +1
        val day = binding.addMedicineDatePicker.dayOfMonth

        val time = "$year-$month-$day $hour:$min:00" // 알람이 울리는 시간
        Log.d("set time : ", time)

        val medicineName = binding.addMedicineItemTitleData.text.toString()
        val medicineDesc = binding.addMedicineItemDescData.text.toString()

        val newMedicineItem = MedicineListItem(time,medicineName,medicineDesc)

        medicineViewModel.setMedicineItemList(newMedicineItem,medicineViewModel.clickedMedicineItem.value!!)

        val random = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
        val alarmCode = random.random()
        val content = medicineDesc
        setAlarm(alarmCode, content, time)
        medicineViewModel.setclickedMedicineItem(-1)
    }

    override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
    }
}