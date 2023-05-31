package com.example.fishfarmapplication.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fishfarmapplication.ui.main.recyclerviews.HomeListItem
import com.example.fishfarmapplication.ui.main.recyclerviews.MedicineListItem
import java.text.SimpleDateFormat

class MedicineViewModel : ViewModel() {
    private var _medicineRemainTime = MutableLiveData<Float>()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")

    val medicineRemainTime : LiveData<Float> get() = _medicineRemainTime

    private val _medicineItemList = MutableLiveData<ArrayList<MedicineListItem>>()

    val medicineItemList : LiveData<ArrayList<MedicineListItem>> get() = _medicineItemList

    init {
        _medicineRemainTime.value = 120608F //DD HH MM


        val medicineItems = arrayListOf(
            MedicineListItem("2023-05-31 12:13:00","test알람","테스트알람입니다~")
        )
        _medicineItemList.value = medicineItems
    }

    fun setMedicineItemList(items:ArrayList<MedicineListItem>){
        _medicineItemList.value = items
    }

    fun addMedicineItemList(item:MedicineListItem){
        _medicineItemList.value!!.add(item)
    }


}