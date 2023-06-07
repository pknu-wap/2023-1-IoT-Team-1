package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListItem
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class MedicineViewModel : ViewModel() {
    private var _medicineRemainTime = MutableLiveData<Float>()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")

    val medicineRemainTime : LiveData<Float> get() = _medicineRemainTime

    private val _clickedMedicineItem = MutableLiveData<Int>()
    val clickedMedicineItem : LiveData<Int> get() = _clickedMedicineItem

    private val _medicineItemList = MutableLiveData<ArrayList<MedicineListItem>>()

    val medicineItemList : LiveData<ArrayList<MedicineListItem>> get() = _medicineItemList

    init {
        _medicineRemainTime.value = 120608F //DD HH MM

        _clickedMedicineItem.value = -1

        val medicineItems = arrayListOf(
            MedicineListItem("2023-10-31 12:13:00","test알람","테스트알람입니다~")
        )
        _medicineItemList.value = medicineItems
    }

    companion object{
        val yearStringFormatter = SimpleDateFormat("yyyy년MM월dd일")
        val hourStringFormatter = SimpleDateFormat("HH:mm")
        val timeStringFormatter = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        val aaStringFormat = SimpleDateFormat("aa")
    }

    fun setMedicineItemList(item:MedicineListItem, position: Int){
        _medicineItemList.value!!.set(position,item)
    }

    fun addMedicineItemList(item:MedicineListItem){
        _medicineItemList.value!!.add(item)
    }

    fun setclickedMedicineItem(int: Int){
        _clickedMedicineItem.value = int
    }
    fun removeMedicineItem(position: Int){
        _medicineItemList.value!!.removeAt(position)
    }

    fun getMinTime() : Date{
        val list = _medicineItemList.value!!
        var min = timeStringFormatter.parse(list[0].time)
        list.forEach({
            val indexTime = timeStringFormatter.parse(it.time)
            if(min < indexTime)
                min = indexTime
        })
        return min
    }

}