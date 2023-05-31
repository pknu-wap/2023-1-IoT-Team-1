package com.example.fishfarmapplication.ui.main.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.fishfarmapplication.databinding.FragmentDialogHomeCenterBinding
import com.example.fishfarmapplication.databinding.FragmentDialogMedicineCenterBinding
import com.example.fishfarmapplication.ui.main.alarm.AlarmFunctions
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListItem
import com.example.fishfarmapplication.ui.main.viewmodels.HomeViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.MedicineViewModel

class MedicineAddTimeDialog :DialogFragment() {
    private var _binding: FragmentDialogMedicineCenterBinding? = null

    private val medicineViewModel : MedicineViewModel by activityViewModels()

    private lateinit var alarmFunctions : AlarmFunctions

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogMedicineCenterBinding.inflate(LayoutInflater.from(context))
        alarmFunctions = AlarmFunctions(requireContext())
        context?.dialogFragmentResize(this@MedicineAddTimeDialog, 0.9f, 0.9f)

//        val sumbitBtn = binding.
//        sumbitBtn.setOnClickListener{
//            dismiss()
//        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()

//        val hour = binding.medicineTimePicker.hour.toString()
//        val min = binding.medicineTimePicker.minute.toString()

        val time = "2023-05-31 12:13:00" // 알람이 울리는 시간

//        MedicineListItem()

        val random = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
        val alarmCode = random.random()
        val content = "테스트 알람"
        setAlarm(alarmCode, content, time)

        _binding = null
    }

    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }

    companion object{
        const val TAG = "MedicineAddTimeDialog"
    }
    fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width: Float, height: Float) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }
    }
}