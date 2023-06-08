package com.example.fishfarmapplication.ui.main.fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.androchef.happytimer.countdowntimer.HappyTimer
import com.example.fishfarmapplication.R
import com.example.fishfarmapplication.databinding.FragmentMedicineBinding
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListAdapter
import com.example.fishfarmapplication.ui.main.recyclerviews.RecyclerViewHolderHightDeco
import com.example.fishfarmapplication.ui.main.viewmodels.MedicineViewModel
import com.example.fishfarmapplication.ui.main.viewmodels.PageViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MedicineFragment : Fragment() {
    private val medicineViewModel : MedicineViewModel by activityViewModels()
    private val PageViewModel : PageViewModel by activityViewModels()
    private lateinit var binding : FragmentMedicineBinding
    companion object{
        val hourStringFormatter = SimpleDateFormat("MM 개월 dd일 HH 시간 mm 분 ss 초")
        val testFormatter = SimpleDateFormat("")
    }

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
            PageViewModel.updageFragmentStatus(PageType.AddMedicine)
        }
        Log.d("RemainTime : ", medicineViewModel.getMinTime().toString())
        val cal = Calendar.getInstance()
        startTimer()

        //아직 -값 리턴 안될 껄 ?
//        val remainMinTime = medicineViewModel.getMinTime()
//        val now = Date(System.currentTimeMillis())
//        Log.d("now : " , now.toString())

//
//        if (remainMinTime.time <= 0)
//            Log.d("tester : ", "모든 알람이 이미 지났기 때문에 생성이 안됨 ")
//        else{
//            val remainTime = (remainMinTime.time -  now.time)
//            binding.medicineRemainTime.base = remainTime
//            binding.medicineRemainTime.setOnChronometerTickListener {
//                val time = binding.medicineRemainTime.base - SystemClock.elapsedRealtime()
//            Log.d("elapsedTime : ",hourStringFormatter.format(SystemClock.elapsedRealtime()))
////            val timeStringFormatter = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
//                binding.medicineRemainTime.setText(hourStringFormatter.format(time))
//            }
//        }
////        Log.d("RemainTimeBase : ", medicineViewModel.getMinTime().time.toString())
//        binding.medicineRemainTime.start()
//        binding.medicineRemainTime.format = "yyyy-MM-dd H:mm:ss"
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

        initRecyclerViewItemTouchHelper()

        val itemDeco = RecyclerViewHolderHightDeco(30)
        binding.medicineRecyclerView.addItemDecoration(itemDeco)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopTimer()
    }

    fun showEditFragment(){
        PageViewModel.updageFragmentStatus(PageType.EditMedicine)
    }

    fun initRecyclerViewItemTouchHelper(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                when(direction){
                    ItemTouchHelper.LEFT->{
                        medicineViewModel.removeMedicineItem(position)
                    }
                }
            }
        })
    }

    fun startTimer(){
        val remainMinTime = medicineViewModel.getMinTime().time
        val now = System.currentTimeMillis()

        binding.medicineRemainTime.base = remainMinTime - now
        binding.medicineRemainTime.setOnChronometerTickListener {
            val prevTime = binding.medicineRemainTime.text.toString()
//            val formatter = DateTimeFormatter.ofPattern("H:mm:ss")
//            val date = LocalDateTime.parse(prevTime,formatter)

//            Log.d("medicineFragment : ", prevTime.toString())
//            Log.d("medicineFragment : ", date.toString())

//            binding.medicineRemainTime.setText(hourStringFormatter.format(time))
        }
        binding.medicineRemainTime.start()
    }

    fun stopTimer(){
        binding.medicineRemainTime.stop()
    }
}